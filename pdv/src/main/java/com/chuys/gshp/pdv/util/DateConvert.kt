package com.chuys.gshp.pdv.util

import com.chuys.gshp.shared.domain.constant.StringConstant
import java.text.SimpleDateFormat
import java.util.*

class DateConvert {

    fun getHourAndMinutes(dateMilli: Long):String {
        val dateFormat=SimpleDateFormat(StringConstant.HOURMINUTEFORMAT)
        var date=Date(dateMilli)
        return dateFormat.format(date)
    }

}