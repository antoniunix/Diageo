package com.chuys.gshp.sku.view

import android.text.Editable
import android.text.TextWatcher
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
            ), MyCustomEditTextListener()
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolderListPdv, position: Int) {
        holder.bin(items.get(position))
        holder.textListener.updatePosition(position)
        if (items.get(position).price != null) {
            holder.itemView.price_edittext.text =
                Editable.Factory.getInstance().newEditable(items.get(position).price)
        } else {
            holder.itemView.price_edittext.text = Editable.Factory.getInstance().newEditable("0")
        }

    }

    inner class ViewHolderListPdv(view: View, customListener: MyCustomEditTextListener) :
        RecyclerView.ViewHolder(view) {
        val textListener: MyCustomEditTextListener = customListener

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



            itemView.price_edittext.setDelimiter(false)
            itemView.price_edittext.setSpacing(false)
            itemView.price_edittext.setDecimals(true)
            itemView.price_edittext.setSeparator(".")
            itemView.price_edittext.setCurrency("$")
            itemView.price_edittext.addTextChangedListener(textListener)


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

    inner class MyCustomEditTextListener : TextWatcher {
        private var position: Int = 0

        fun updatePosition(position: Int) {
            this.position = position
        }

        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i2: Int, i3: Int) {
            // no op
        }

        override fun onTextChanged(charSequence: CharSequence, i: Int, i2: Int, i3: Int) {
            items[position].price = charSequence.toString().replace("$", "").replace(",", "")
        }

        override fun afterTextChanged(editable: Editable) {
            // no op
        }
    }
}