package com.chuys.gshp.shared.domain.mapper

interface Mapper<D, E> {
    fun mapTo(type: D): E
    fun mapFrom(type: E): D
}