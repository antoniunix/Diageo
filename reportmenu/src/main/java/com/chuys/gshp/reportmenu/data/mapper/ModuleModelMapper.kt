package com.chuys.gshp.reportmenu.data.mapper

import com.chuys.gshp.navigation.Activities
import com.chuys.gshp.reportmenu.data.model.Module
import com.chuys.gshp.reportmenu.domain.model.Modules
import com.chuys.gshp.shared.domain.constant.IntConstants
import com.chuys.gshp.shared.domain.mapper.Transform

class ModuleModelMapper : Transform<ArrayList<Module>, ArrayList<Modules>>() {


    override fun transform(value: ArrayList<Module>): ArrayList<Modules> {
        var modules = ArrayList<Modules>()
        for (module in value) {
            modules.add(getModules(module))
        }
        return modules
    }

    private fun getModules(module: Module): Modules {
        return Modules(
            module.id,
            module.name,
            0,
            module.order,
            false,
            module.iconActive,
            module.iconInactive,
            if (module.form.size > 1)
                if (module.form.contains(IntConstants.Avaylability)) Activities.PRICE_AND_AVAILABILITY else Activities.DEFAULT
            else Activities.ENCUESTA
        )
    }
}