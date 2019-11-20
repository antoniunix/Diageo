package com.chuys.gshp.sku.data.repository

import com.chuys.gshp.shared.data.database.realtime.RealmTimeDbConfig
import com.chuys.gshp.shared.domain.constant.StringConstant
import com.chuys.gshp.shared.domain.models.Resource
import com.chuys.gshp.sku.data.model.ItemModel
import com.chuys.gshp.sku.domain.model.SkuAvailabilityAndPriceData
import com.chuys.gshp.sku.domain.repository.AvailabilityAndPriceRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import io.reactivex.Single

class AvailabilityAndPriceDataRepository : AvailabilityAndPriceRepository {

    lateinit var referenceDb: DatabaseReference

    override fun getReferenceToDb() {
        referenceDb = RealmTimeDbConfig.getReference("data").child("item")
    }

    override fun getSkuToMesureAvailabilityAndPrice(): Single<Resource<List<SkuAvailabilityAndPriceData>>> {
        getReferenceToDb()
        return getInfoFromRealDataBase()
    }

    override fun updateItemSqlite() {

    }

    private fun getInfoFromRealDataBase(): Single<Resource<List<SkuAvailabilityAndPriceData>>> {
        val moduleList = ArrayList<ItemModel>()
        return Single.create {
            referenceDb.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    var itemModel: ItemModel?
                    for (snapshot in dataSnapshot.children) {
                        try {
                            itemModel = snapshot.getValue(ItemModel::class.java)
                        } catch (e: Exception) {
                            continue
                        }
                        moduleList.add(itemModel!!)
                    }
                    ItemEntity().deleteAll()
                    ItemEntity().writeItem(moduleList)
                    it.onSuccess(
                        Resource.success(
                            ItemEntity().getItem(),
                            StringConstant.EMPTY_STRING
                        )
                    )
                }

                override fun onCancelled(p0: DatabaseError) {
                    it.onError(Throwable("Error"))
                }
            })
        }
    }


    override fun saveAvailabilityAndPrice() {

    }

}