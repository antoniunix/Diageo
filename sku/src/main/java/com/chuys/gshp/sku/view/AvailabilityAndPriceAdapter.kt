package com.chuys.gshp.sku.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chuys.gshp.sku.R
import com.chuys.gshp.sku.domain.constant.Constants
import com.chuys.gshp.sku.domain.model.SkuAvailabilityAndPriceData
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.row_availability_and_price.view.*

class AvailabilityAndPriceAdapter(val items: List<SkuAvailabilityAndPriceData>) :
    RecyclerView.Adapter<AvailabilityAndPriceAdapter.ViewHolderListPdv>() {

    private val clickSubject = PublishSubject.create<SkuAvailabilityAndPriceData>()
    val clickEvent: Observable<SkuAvailabilityAndPriceData> = clickSubject

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderListPdv {
        return ViewHolderListPdv(
            LayoutInflater.from(parent.context).inflate(
                R.layout.row_availability_and_price,
                parent,
                false
            )
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

        fun bin(sku: SkuAvailabilityAndPriceData) {
            itemView.name_textview.text = sku.name

            when (sku.availability) {
                Constants.AVAILABILITY -> {
                    itemView.availability_radio_button.isChecked = true
                    itemView.spent_radio_button.isChecked = false
                }
                Constants.SPENT -> {
                    itemView.availability_radio_button.isChecked = false
                    itemView.spent_radio_button.isChecked = true
                }
                else -> {
                    itemView.availability_radio_button.isChecked = false
                    itemView.spent_radio_button.isChecked = false
                }
            }


            itemView.availability_radio_button.setOnClickListener {
                itemView.spent_radio_button.isChecked = false
                sku.availability = Constants.AVAILABILITY
            }
            itemView.spent_radio_button.setOnClickListener {
                itemView.availability_radio_button.isChecked = false
                sku.availability = Constants.SPENT
            }
        }
    }
}