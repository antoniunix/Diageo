package com.chuys.gshp.sku.data.mapper

import com.chuys.gshp.shared.domain.mapper.Transform
import com.chuys.gshp.sku.data.model.ReportPriceModel
import com.chuys.gshp.sku.domain.model.SkuAvailabilityAndPriceData

class ReportPriceMapper :
    Transform<List<SkuAvailabilityAndPriceData>, List<ReportPriceModel>>() {
    override fun transform(value: List<SkuAvailabilityAndPriceData>): List<ReportPriceModel> {
        val reportPriceList = ArrayList<ReportPriceModel>()
        for (reportAvailability in value) {
            if (reportAvailability.price.isNullOrEmpty()) {
                reportAvailability.price = "0"
            }
            reportPriceList.add(
                ReportPriceModel(
                    reportAvailability.idReport,
                    "",
                    reportAvailability.idSku.toString(),
                    reportAvailability.price!!, System.currentTimeMillis().toString()
                )
            )

        }
        return reportPriceList
    }

}