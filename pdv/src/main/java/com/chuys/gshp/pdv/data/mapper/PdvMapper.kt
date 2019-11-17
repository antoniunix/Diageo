package com.chuys.gshp.pdv.data.mapper

import com.chuys.gshp.pdv.data.model.PdvData
import com.chuys.gshp.pdv.domain.model.PdvModel
import com.chuys.gshp.shared.domain.mapper.Transform

class PdvMapper : Transform<ArrayList<PdvData>, ArrayList<PdvModel>>() {
    override fun transform(value: ArrayList<PdvData>): ArrayList<PdvModel> {

        var pdv = ArrayList<PdvModel>()
        for (item in value) {
            pdv.add(getPdv(item))
        }
        return pdv
    }

    private fun getPdv(value: PdvData): PdvModel {
        return PdvModel(
            1,
            value.name,
            1,
            "cliente 1",
            "03300",
            value.code,
            0,
            value.address,
            value.lat,
            value.lon
        )
    }

}