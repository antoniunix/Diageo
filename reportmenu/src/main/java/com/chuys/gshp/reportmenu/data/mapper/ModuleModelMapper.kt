package com.chuys.gshp.reportmenu.data.mapper

import com.chuys.gshp.navigation.Activities
import com.chuys.gshp.reportmenu.data.model.Module
import com.chuys.gshp.reportmenu.domain.model.Modules
import com.chuys.gshp.shared.domain.constant.IntConstants
import com.chuys.gshp.shared.domain.mapper.Transform

class ModuleModelMapper : Transform<ArrayList<Module>, ArrayList<Modules>>() {


    override fun transform(value: ArrayList<Module>): ArrayList<Modules> {
        var modules = ArrayList<Modules>()
        for (module in value)
            modules.add(getModules(module))
        return modules
    }

    private fun getModules(module: Module): Modules {
        val codeModule = getCodeModule(module.form)
        return Modules(
            module.id,
            module.name,
            0,
            module.order,
            false,
            module.iconActive,
            module.iconInactive,
            codeModule
        )
    }

    private fun getCodeModule(form: List<Int>): Activities {
        return when {
            IntConstants.Avaylability in form -> Activities.PRICE_AND_AVAILABILITY
            IntConstants.POLL in form -> Activities.POLL
            IntConstants.EJECUTABLE in form -> Activities.EXECUTABLE
            else -> Activities.DEFAULT
        }
    }
}