package com.chuys.gshp.sku.view

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chuys.gshp.shared.data.job.JobExecutor
import com.chuys.gshp.shared.data.job.UIThread
import com.chuys.gshp.shared.util.commons.ToolbarHelper
import com.chuys.gshp.sku.R
import com.chuys.gshp.sku.data.provider.AvailabilityAndPriceDataProvider
import com.chuys.gshp.sku.domain.model.SkuAvailabilityAndPriceData
import com.chuys.gshp.sku.domain.provider.AvailabilityAndPriceProvider
import com.chuys.gshp.sku.presenter.Presenter
import com.chuys.gshp.sku.presenter.contract.AvailabilityAndPriceContract
import kotlinx.android.synthetic.main.activity_sku.*


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
        ToolbarHelper(this).configToolbarHelpGeneric(R.string.app_name_availability, true, 0)

        listSkuRecyclerView = findViewById(R.id.list_sku_recycler)
        presenter = Presenter(this, availabilityAndPriceProvider)

    }


    override fun onResume() {
        super.onResume()
        presenter.getMeasurementSku()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        val mSearch = menu?.findItem(R.id.action_search)
        return super.onCreateOptionsMenu(menu)

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
