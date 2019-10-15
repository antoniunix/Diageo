package com.chuys.gshp.executables.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chuys.gshp.executables.R
import com.chuys.gshp.executables.domain.model.ExecutableData
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.row_executable.view.*

class ExecutableAdapter(val items: List<ExecutableData>) :
    RecyclerView.Adapter<ExecutableAdapter.ViewHolderExecutable>() {

    private val clickSubject = PublishSubject.create<ExecutableData>()
    val clickEvent: Observable<ExecutableData> = clickSubject

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderExecutable {
        return ViewHolderExecutable(
            LayoutInflater.from(parent.context).inflate(R.layout.row_executable, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolderExecutable, position: Int) {
        holder.bin(items.get(position))
    }

    inner class ViewHolderExecutable(view: View) : RecyclerView.ViewHolder(view) {
        init {
            itemView.take_photo_button.setOnClickListener {
                clickSubject.onNext(items[layoutPosition])
            }
        }

        fun bin(executableData: ExecutableData) {
            itemView.name_textview.text = executableData.name
            itemView.take_photo_button.setText("${executableData.numPhotos}   de   ${executableData.donePhotos}")

            itemView.enable_radio_button.setOnClickListener {
                if (itemView.disable_radio_button.isChecked) {
                    itemView.disable_radio_button.isChecked = false
                    executableData.done = true
                }
            }
            itemView.disable_radio_button.setOnClickListener {
                if (itemView.enable_radio_button.isChecked) {
                    itemView.enable_radio_button.isChecked = false
                    executableData.done = false
                }
            }
        }
    }

}