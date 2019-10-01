package com.chuys.gshp.shared.domain.models

class Resource<T>(
    var isSuccess: Boolean = false,
    var data: T? = null,
    var errorMsg: String? = null
) {
    companion object {

        /**
         * Creates [Resource] object with `SUCCESS` status, [data] and [ServiceResponse].
         */
        @JvmStatic
        fun <T> success(data: T? = null, errorMsg: String): Resource<T> {
            return Resource(
                true,
                data,
                errorMsg
            )
        }

        /**
         * Creates [Resource] object with `ERROR` status and [ServiceResponse].
         */
        @JvmStatic
        fun <T> error(errorMsg: String): Resource<T> {
            return Resource(
                false,
                null,
                errorMsg
            )
        }
    }
}