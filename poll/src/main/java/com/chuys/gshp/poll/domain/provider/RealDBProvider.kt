package com.chuys.gshp.poll.domain.provider

import com.chuys.gshp.poll.domain.model.Form
import com.chuys.gshp.shared.domain.usecase.SingleUseCase

interface RealDBProvider {
    fun getData(): SingleUseCase<Any, List<Form>>
}