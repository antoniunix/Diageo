package com.chuys.gshp.comunication.view

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chuys.gshp.comunication.data.model.MediaData
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class MediaTypeAdapter(val items: List<MediaData>) :
    RecyclerView.Adapter<MediaTypeAdapter.ViewHolderListMedia>() {

    private val clickSubject = PublishSubject.create<MediaData>()
    val clickEvent: Observable<MediaData> = clickSubject


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaTypeAdapter.ViewHolderListMedia {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: MediaTypeAdapter.ViewHolderListMedia, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    inner class ViewHolderListMedia(view: View):RecyclerView.ViewHolder(view){
        init {
            itemView.setOnClickListener{
                clickSubject.onNext(items[layoutPosition])
            }
        }
    }

}