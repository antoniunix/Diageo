package com.chuys.gshp.reportmenu.domain.repository

import com.chuys.gshp.reportmenu.domain.model.Modules
import com.chuys.gshp.shared.domain.models.Resource
import com.google.firebase.database.DatabaseReference
import io.reactivex.Single

interface MenuReportRepository{
    fun getReferenceToDb()
    fun getModules(): Single<Resource<ArrayList<Modules>>>
}