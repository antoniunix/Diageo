package com.chuys.gshp.comunication.data.repository

import com.chuys.gshp.comunication.data.mapper.MediaMapper
import com.chuys.gshp.comunication.data.model.MediaData
import com.chuys.gshp.comunication.data.model.MediaTypeData
import com.chuys.gshp.comunication.domain.model.ComunicationModel
import com.chuys.gshp.comunication.domain.model.MediaModel
import com.chuys.gshp.comunication.domain.repository.MediaRepository
import com.chuys.gshp.shared.data.database.realtime.RealmTimeDbConfig
import com.chuys.gshp.shared.domain.constant.StringConstant
import com.chuys.gshp.shared.domain.models.Resource
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import io.reactivex.Single

class MediaDataRepository : MediaRepository {

    lateinit var referenceMedia: DatabaseReference
    lateinit var referenceMediaType: DatabaseReference
    val mapper = MediaMapper()

    override fun getAllMedia(): Single<Resource<List<ComunicationModel>>> {
        getReferenceToDb()
        return getInfoFromRealDataBase()
    }

    override fun getReferenceToDb() {
        referenceMedia = RealmTimeDbConfig.getReference("data").child("media")
        referenceMediaType = RealmTimeDbConfig.getReference("data").child("mediaType")
    }

    private fun getInfoFromRealDataBase(): Single<Resource<List<ComunicationModel>>> {
        val mediaDatalList = ArrayList<MediaData>()
        val mediaTypeList = ArrayList<MediaTypeData>()
        var isMediaComplete = false
        var isMediaTypeComplete = false
        return Single.create {
            referenceMediaType.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    var itemType: MediaTypeData?
                    for (snapshot in p0.children) {
                        try {
                            itemType = snapshot.getValue(MediaTypeData::class.java)
                            itemType?.id = snapshot.key?.toInt()
                        } catch (e: Exception) {
                            continue
                        }
                        mediaTypeList.add(itemType!!)
                    }
                    MediaTypeEntity().deleteAll()
                    MediaTypeEntity().writeItem(mediaTypeList)
                    isMediaTypeComplete = true
                    if(isMediaComplete){
                        it.onSuccess(Resource.success(
                            MediaEntity().getItem(), StringConstant.EMPTY_STRING
                        ))
                    }
                }

                override fun onCancelled(p0: DatabaseError) {
                    it.onError(Throwable("Error"))
                }
            })

            referenceMedia.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    var mediaModel: MediaData?
                    for (snapshot in dataSnapshot.children) {
                        try {
                            mediaModel = snapshot.getValue(MediaData::class.java)
                            mediaModel?.id = snapshot.key?.toInt()
                        } catch (e: Exception) {
                            continue
                        }
                        mediaDatalList.add(mediaModel!!)
                    }
                    MediaEntity().deleteAll()
                    MediaEntity().writeMedia(mediaDatalList)
                    isMediaComplete = true
                    if (isMediaTypeComplete)
                        it.onSuccess(
                            Resource.success(
                                //MediaEntity().writeMedia()
                                MediaEntity().getItem(), StringConstant.EMPTY_STRING
                            )
                        )
                }

                override fun onCancelled(p0: DatabaseError) {
                    it.onError(Throwable("Error"))
                }
            })


        }

    }

}