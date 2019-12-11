package com.chuys.gshp.comunication.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chuys.gshp.comunication.R
import com.chuys.gshp.comunication.domain.model.MediaModel
import com.chuys.gshp.shared.domain.constant.IntConstants
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.row_media.view.*

class MediaAdapter(val items: List<MediaModel>) :
    RecyclerView.Adapter<MediaAdapter.ViewHolderListMedia>() {

    private val clickSubject = PublishSubject.create<MediaModel>()
    val clickEvent: Observable<MediaModel> = clickSubject
    var mediaType: Int = 0


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

        fun bin(media: MediaModel) {
            if (mediaType != media.mediaType) {
               when (media.mediaType) {
                    1 ->  itemView.txt_title.text="PDF"
                    2 ->  itemView.txt_title.text="JPG"
                    3 ->  itemView.txt_title.text="PNG"
                    4 ->  itemView.txt_title.text="MP4"
                    5 ->  itemView.txt_title.text="HTML"
                    6 ->  itemView.txt_title.text="DOCX"
                    else -> itemView.txt_title.text= "Otros"
                }
            } else {
                itemView.txt_description.text = media.description
                itemView.txt_title.text = media.title
            }
            mediaType=media.mediaType
        }
    }
}