package com.chuys.gshp.visits.view.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chuys.gshp.visits.R
import com.chuys.gshp.visits.view.domain.model.VisitModel
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.row_list_visit.view.*

class VisitAdapter(val items: List<VisitModel>) :
    RecyclerView.Adapter<VisitAdapter.ViewHolderVisit>() {

    private val clickSubject = PublishSubject.create<VisitModel>()
    val clickEvent: Observable<VisitModel> = clickSubject

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderVisit {
        return ViewHolderVisit(
            LayoutInflater.from(parent.context).inflate(R.layout.row_list_visit, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolderVisit, position: Int) {
        holder.bin(items.get(position))
    }

    inner class ViewHolderVisit(view: View) : RecyclerView.ViewHolder(view) {
        init {
            itemView.setOnClickListener {
                clickSubject.onNext(items[layoutPosition])
            }
        }

        fun bin(visit: VisitModel) {
            itemView.pdvvisitedTextView.text = visit.pdvName
            itemView.createdTextView.text=visit.dateCheckin.toString()
        }
    }
}
