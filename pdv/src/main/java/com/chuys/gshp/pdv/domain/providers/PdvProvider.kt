package com.chuys.gshp.pdv.domain.providers

import com.chuys.gshp.pdv.domain.usescases.ListPdvUseCase

interface PdvProvider {
    fun getListPdvUseCase(): ListPdvUseCase
}