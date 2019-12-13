package com.chuys.gshp.comunication.data.repository

import com.chuys.gshp.comunication.data.mapper.MediaMapper
import com.chuys.gshp.comunication.data.model.MediaData
import com.chuys.gshp.comunication.data.model.MediaTypeData
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

    override fun getAllMedia(): Single<Resource<List<MediaModel>>> {
        getReferenceToDb()
        return getInfoFromRealDataBase()
    }

    override fun getReferenceToDb() {
        referenceMedia = RealmTimeDbConfig.getReference("data").child("media")
        referenceMediaType = RealmTimeDbConfig.getReference("data").child("mediaType")
    }

    private fun getInfoFromRealDataBase(): Single<Resource<List<MediaModel>>> {
        val modulList = ArrayList<MediaData>()
        val modulTypeList = ArrayList<MediaTypeData>()
        var isMediaComplete = false
        var isMediaTypeComplete = false
        return Single.create {

            referenceMediaType.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    var itemType: MediaTypeData?
                    for (snapshot in dataSnapshot.children) {
                        try {
                            itemType = snapshot.getValue(MediaTypeData::class.java)
                            itemType?.id = snapshot.key?.toInt()
                        } catch (e: Exception) {
                            continue
                        }
                        modulTypeList.add(itemType!!)
                    }
                    MediaTypeEntity().deleteAll()
                    MediaTypeEntity().writeItem(modulTypeList)
                    isMediaTypeComplete = true
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
                            mediaModel?.id = snapshot.key?.toLong()
                        } catch (e: Exception) {
                            continue
                        }
                        modulList.add(mediaModel!!)
                    }
                    MediaEntity().deleteAll()
                    MediaEntity().writeMedia(modulList)
                    isMediaComplete = true
                    if (isMediaTypeComplete)
                        it.onSuccess(
                            Resource.success(
                               //MediaEntity().writeMedia()
                                mapper.transform(modulList), StringConstant.EMPTY_STRING
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