package com.chuys.gshp.reportmenu.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chuys.gshp.reportmenu.R
import com.chuys.gshp.reportmenu.domain.model.Modules
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.row_report_menu.view.*

class ReportMenuAdapter(val items: List<Modules>) : RecyclerView.Adapter<ReportMenuAdapter.ViewHolderListPdv>() {

    private val clickSubject = PublishSubject.create<Modules>()
    val clickEvent: Observable<Modules> = clickSubject

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderListPdv {
        return ViewHolderListPdv(
            LayoutInflater.from(parent.context).inflate(R.layout.row_report_menu, parent, false)
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

        fun bin(modules: Modules) {
            itemView.name_textview.text = modules.name
        }
    }

}