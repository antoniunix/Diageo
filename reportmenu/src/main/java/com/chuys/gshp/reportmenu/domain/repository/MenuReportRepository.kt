package com.chuys.gshp.reportmenu.domain.repository

import com.chuys.gshp.reportmenu.domain.model.Modules
import com.chuys.gshp.shared.domain.models.Resource
import io.reactivex.Single

interface MenuReportRepository{
    fun getModules(): Single<Resource<List<Modules>>>
}