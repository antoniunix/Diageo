package com.chuys.gshp.sku.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chuys.gshp.sku.R
import com.chuys.gshp.sku.data.provider.AvailabilityAndPriceDataProvider
import com.chuys.gshp.sku.domain.model.SkuAvailabilityAndPriceData
import com.chuys.gshp.sku.domain.provider.AvailabilityAndPriceProvider
import com.chuys.gshp.sku.presenter.Presenter
import com.chuys.gshp.sku.presenter.contract.AvailabilityAndPriceContract
import com.chuys.gshp.shared.data.job.JobExecutor
import com.chuys.gshp.shared.data.job.UIThread

class Sku : AppCompatActivity(), AvailabilityAndPriceContract.ViewContract {

    lateinit var availabilityAndPriceProvider: AvailabilityAndPriceProvider
    lateinit var presenter: AvailabilityAndPriceContract.PresenterContract
    lateinit var listSkuRecyclerView: RecyclerView
    private var listAdapter: AvailabilityAndPriceAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sku)

        availabilityAndPriceProvider = AvailabilityAndPriceDataProvider(
            JobExecutor(),
            UIThread()
        )
        listSkuRecyclerView=findViewById(R.id.list_sku_recycler)
        presenter=Presenter(this,availabilityAndPriceProvider)
    }

    override fun onResume() {
        super.onResume()
        presenter.getMeasurementSku()
    }

    override fun loadRecyclerView(sku: List<SkuAvailabilityAndPriceData>) {
        listAdapter = AvailabilityAndPriceAdapter(sku)
        listSkuRecyclerView.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(this@Sku)
            //presenter.clickItemAdapter(listAdapter as RecyclerView.Adapter<RecyclerView.ViewHolder>,this@ListPdv)
        }
    }

    override fun showError() {
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }
}
