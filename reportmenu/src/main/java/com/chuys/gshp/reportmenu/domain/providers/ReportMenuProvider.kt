package com.chuys.gshp.reportmenu.domain.providers

import com.chuys.gshp.reportmenu.domain.model.Modules
import com.chuys.gshp.shared.domain.models.Resource
import com.chuys.gshp.shared.domain.usecase.SingleUseCase

interface ReportMenuProvider {
    fun getModulerUseCase(): SingleUseCase<Any, Resource<List<Modules>>>
}