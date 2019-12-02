package com.chuys.gshp.pdv.view

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.chuys.gshp.pdv.R
import com.chuys.gshp.pdv.presenter.contract.CheckContract
import com.chuys.gshp.pdv.util.DateConvert
import com.chuys.gshp.shared.domain.constant.IntConstants
import com.chuys.gshp.shared.domain.constant.StringConstant
import kotlinx.android.synthetic.main.dialog_finish_report.*

class DialogFinishReport(private val checkOutContract: CheckContract.CheckOutContract) :DialogFragment(),View.OnClickListener{
    private var dateCheck:String?=""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView=inflater.inflate(R.layout.dialog_finish_report,container)
        dateCheck=arguments?.getString(StringConstant.KEYBUNDLE)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txt_time_checkin.setText(dateCheck)
        txt_time_checkout.setText(DateConvert().getHourAndMinutes(System.currentTimeMillis()))
        btn_no.setOnClickListener(this)
        btn_yes.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
       when (v?.id){
           R.id.btn_no->this.dismiss()
           R.id.btn_yes->{
            checkOutContract.saveCheckout()
           this.dismiss()
           }
       }
    }

}