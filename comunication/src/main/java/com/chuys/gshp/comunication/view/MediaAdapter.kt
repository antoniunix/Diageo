package com.chuys.gshp.comunication.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chuys.gshp.comunication.R
import com.chuys.gshp.comunication.domain.model.ComunicationModel
import com.chuys.gshp.comunication.domain.model.MediaModel
import com.chuys.gshp.shared.domain.constant.IntConstants
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.row_media.view.*

class MediaAdapter(val items: List<ComunicationModel>) :
    RecyclerView.Adapter<MediaAdapter.ViewHolderListMedia>() {

    private val clickSubject = PublishSubject.create<ComunicationModel>()
    val clickEvent: Observable<ComunicationModel> = clickSubject
    var mediaType: String = ""


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderListMedia {
        return ViewHolderListMedia(
            LayoutInflater.from(parent.context).inflate(
                R.layout.row_media, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolderListMedia, position: Int) {
        holder.bin(items.get(position))
    }

    inner class ViewHolderListMedia(view: View) : RecyclerView.ViewHolder(view) {
        init {
            itemView.setOnClickListener {
                clickSubject.onNext(items[layoutPosition])
            }
        }

        fun bin(media: ComunicationModel) {
            if (!mediaType.equals(media.mediaType)) {
                itemView.txt_title.text = media.mediaType.toUpperCase().replace(".","")
            } else {
                itemView.txt_description.text = media.description
                itemView.txt_title.text = media.title
            }
            mediaType = media.mediaType
        }
    }
}