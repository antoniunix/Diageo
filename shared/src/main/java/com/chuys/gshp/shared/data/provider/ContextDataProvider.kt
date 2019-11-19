package com.chuys.gshp.shared.data.provider

import android.content.Context
import com.chuys.gshp.shared.domain.provider.ContextProvider

class ContextDataProvider constructor(private val appcontext:Context): ContextProvider{


    override fun getContext(): Context {
       return appcontext
    }

}