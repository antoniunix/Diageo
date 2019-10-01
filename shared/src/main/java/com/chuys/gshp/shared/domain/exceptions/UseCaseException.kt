package com.chuys.gshp.shared.domain.exceptions

sealed class UseCaseException : kotlin.Exception() {
    class GenericException(val code: Int = 0, val errorMessage: String = "") : Exception(errorMessage)
}