package com.chuys.gshp.visits.view.presenter.contract

import com.chuys.gshp.visits.view.domain.model.VisitModel

interface VisitContract {

    interface VisitPresenterContract{

    }

    interface VisitViewContractt{
        fun loadRecyclerView(visit:List<VisitModel>)

    }

}