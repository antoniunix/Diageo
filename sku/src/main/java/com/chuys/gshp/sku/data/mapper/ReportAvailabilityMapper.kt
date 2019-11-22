package com.chuys.gshp.sku.data.mapper

import com.chuys.gshp.shared.domain.mapper.Transform
import com.chuys.gshp.sku.data.model.ReportAvailabilityModel
import com.chuys.gshp.sku.domain.model.SkuAvailabilityAndPriceData

class ReportAvailabilityMapper :
    Transform<List<SkuAvailabilityAndPriceData>, List<ReportAvailabilityModel>>() {
    override fun transform(value: List<SkuAvailabilityAndPriceData>): List<ReportAvailabilityModel> {
        val reportAvailabilityList = ArrayList<ReportAvailabilityModel>()

        for (reportAvailability in value) {
            reportAvailabilityList.add(
                ReportAvailabilityModel(
                    reportAvailability.idReport,
                    "",
                    reportAvailability.idSku.toString(),
                    reportAvailability.availability!!, System.currentTimeMillis().toString()
                )
            )
        }
        return reportAvailabilityList
    }

}