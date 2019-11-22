package com.chuys.gshp.shared.domain.repository

import com.chuys.gshp.shared.data.model.Form
import io.reactivex.Single

interface RealDBRepository {
    fun getReference()
    fun getData(): Single<List<Form>>
}