package com.chuys.gshp.poll.domain.repository

import com.chuys.gshp.poll.domain.model.Form
import io.reactivex.Single

interface RealDBRepository {
    fun getReference()
    fun getData(): Single<List<Form>>
}