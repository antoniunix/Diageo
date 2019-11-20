package com.chuys.gshp.sku.view

import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.chuys.gshp.sku.R
import com.chuys.gshp.sku.domain.constant.Constants
import com.chuys.gshp.sku.domain.model.SkuAvailabilityAndPriceData
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.row_availability_and_price.view.*
import android.text.Selection.getSelectionStart
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import me.abhinay.input.CurrencyEditText
import java.text.DecimalFormat
import java.text.ParseException
import java.util.*


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


            itemView.price_edittext.text = Editable.Factory.getInstance().newEditable(sku.price)
            itemView.price_edittext.setDelimiter(false)
            itemView.price_edittext.setSpacing(false)
            itemView.price_edittext.setDecimals(true)
            itemView.price_edittext.setSeparator(".")
            itemView.price_edittext.addTextChangedListener { charSequence ->
                try {
                    sku.price = charSequence.toString()
                } catch (error: Throwable) {
                    sku.price = "0.0"
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