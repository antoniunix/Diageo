package com.chuys.gshp.shared.domain.provider

import com.chuys.gshp.shared.data.model.Form
import com.chuys.gshp.shared.domain.usecase.SingleUseCase

interface RealDBProvider {
    fun getData(): SingleUseCase<Any, List<Form>>
}