package com.chuys.gshp.comunication.data.repository

import com.chuys.gshp.comunication.domain.model.MediaModel
import com.chuys.gshp.comunication.domain.model.MediaTypeModel
import com.chuys.gshp.comunication.domain.repository.MediaRepository
import com.chuys.gshp.shared.data.database.realtime.RealmTimeDbConfig
import com.chuys.gshp.shared.domain.models.Resource
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import io.reactivex.Single
import java.lang.Exception

class MediaDataRepository: MediaRepository {

    lateinit var referenceMedia:DatabaseReference
    lateinit var referenceMediaType:DatabaseReference

    override fun getAllMedia(): Single<Resource<List<MediaModel>>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun getReferenceToDb() {
      referenceMedia=RealmTimeDbConfig.getReference("data").child("media")
      referenceMediaType=RealmTimeDbConfig.getReference("data").child("mediaType")
    }

    private fun getInfoFromRealDataBase(): Single<Resource<List<MediaModel>>>{
        val modulList= ArrayList<MediaModel>()
        val moduleMediaType=ArrayList<MediaTypeModel>()
        var isItemComplete = false
        var isItemPropsComplete = false
        return Single.create{
            referenceMedia.addValueEventListener(object :ValueEventListener{
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                   var mediaModel: MediaModel?
                    for(snapshot in dataSnapshot.children){
                        try {
                            mediaModel=snapshot.getValue(MediaModel::class.java)
                        }catch (e:Exception){
                            continue
                        }
                        modulList.add(mediaModel!!)
                    }

                }
                override fun onCancelled(p0: DatabaseError) {
                    it.onError(Throwable("Error"))
                }

            })
        }

    }

}