package com.chuys.gshp.pdv.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chuys.gshp.pdv.R
import com.chuys.gshp.pdv.contract.PdvContract
import com.chuys.gshp.pdv.data.provider.PdvDataProvider
import com.chuys.gshp.pdv.domain.providers.PdvProvider
import com.chuys.gshp.pdv.presenter.Presenter
import com.chuys.gshp.shared.data.job.JobExecutor
import com.chuys.gshp.shared.data.job.UIThread

class ListPdv : AppCompatActivity(), PdvContract.PdvViewContract {

    private lateinit var pdvProvider: PdvProvider
    private lateinit var presenter: PdvContract.PdvPresenterContract

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_pdv)
        pdvProvider = PdvDataProvider(
            JobExecutor(),
            UIThread()
        )
        presenter = Presenter(this, pdvProvider)
        presenter.start()
    }

    override fun onResume() {
        super.onResume()
        presenter.getAllPdv()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }
}
