package com.chuys.gshp.sku.data.repository

import android.util.Log
import com.chuys.gshp.shared.data.database.realtime.RealmTimeDbConfig
import com.chuys.gshp.shared.domain.constant.StringConstant
import com.chuys.gshp.shared.domain.models.Resource
import com.chuys.gshp.sku.data.model.ItemModel
import com.chuys.gshp.sku.data.model.ItemPropsModel
import com.chuys.gshp.sku.domain.model.SkuAvailabilityAndPriceData
import com.chuys.gshp.sku.domain.repository.AvailabilityAndPriceRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import io.reactivex.Single

class AvailabilityAndPriceDataRepository : AvailabilityAndPriceRepository {

    lateinit var referenceItemDb: DatabaseReference
    lateinit var referenceItemPropsDb: DatabaseReference

    override fun getReferenceToDb() {
        referenceItemDb = RealmTimeDbConfig.getReference("data").child("item")
        referenceItemPropsDb = RealmTimeDbConfig.getReference("data").child("itemProps")
    }

    override fun getSkuToMesureAvailabilityAndPrice(): Single<Resource<List<SkuAvailabilityAndPriceData>>> {
        getReferenceToDb()
        return getInfoFromRealDataBase()
    }

    override fun updateItemSqlite() {

    }

    private fun getInfoFromRealDataBase(): Single<Resource<List<SkuAvailabilityAndPriceData>>> {
        val moduleList = ArrayList<ItemModel>()
        val moduleItemPropsList = ArrayList<ItemPropsModel>()
        var isItemComplete = false
        var isItemPropsComplete = false
        return Single.create {
            referenceItemDb.addValueEventListener(object : ValueEventListener {
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
                    isItemComplete=true
                    if(isItemPropsComplete){
                        it.onSuccess(
                            Resource.success(
                                ItemEntity().getItem(),
                                StringConstant.EMPTY_STRING
                            )
                        )
                    }
                }

                override fun onCancelled(p0: DatabaseError) {
                    it.onError(Throwable("Error"))
                }
            })
            referenceItemPropsDb.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    var itemModel: ItemPropsModel?
                    for (snapshot in dataSnapshot.children) {
                        try {
                            itemModel = snapshot.getValue(ItemPropsModel::class.java)
                            itemModel?.id=snapshot.key?.toLong()
                        } catch (e: Exception) {
                            continue
                        }
                        moduleItemPropsList.add(itemModel!!)
                    }
                    ItemPropsEntity().deleteAll()
                    ItemPropsEntity().writeItem(moduleItemPropsList)
                    isItemPropsComplete=true
                    if(isItemComplete){
                        it.onSuccess(
                            Resource.success(
                                ItemEntity().getItem(),
                                StringConstant.EMPTY_STRING
                            )
                        )
                    }
                }

                override fun onCancelled(p0: DatabaseError) {

                }
            })
        }
    }


    override fun saveAvailabilityAndPrice() {

    }

}