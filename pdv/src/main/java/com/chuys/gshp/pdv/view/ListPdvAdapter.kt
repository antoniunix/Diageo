package com.chuys.gshp.pdv.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chuys.gshp.pdv.R
import com.chuys.gshp.pdv.domain.model.PdvModel
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.row_list_pdv.view.*

class ListPdvAdapter(val items: List<PdvModel>) : RecyclerView.Adapter<ListPdvAdapter.ViewHolderListPdv>() {

    private val clickSubject = PublishSubject.create<PdvModel>()
    val clickEvent: Observable<PdvModel> = clickSubject

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderListPdv {
        return ViewHolderListPdv(
            LayoutInflater.from(parent.context).inflate(R.layout.row_list_pdv, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolderListPdv, position: Int) {
        holder.bin(items.get(position))
    }

    inner class ViewHolderListPdv(view: View) : RecyclerView.ViewHolder(view) {
        init {
            itemView.setOnClickListener {
                clickSubject.onNext(items[layoutPosition])
            }
        }
        fun bin(pdvData: PdvModel) {
            itemView.name_textview.text = pdvData.name
            itemView.addressTextView.text = pdvData.address
        }
    }

}