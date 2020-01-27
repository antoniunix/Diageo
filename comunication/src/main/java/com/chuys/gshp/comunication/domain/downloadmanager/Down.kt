package com.chuys.gshp.comunication.domain.downloadmanager

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.net.Uri
import android.os.Environment
import com.chuys.gshp.shared.domain.constant.StringConstant
import java.io.File

fun initDownLoadRequest(url: String, title: String, file: File): DownloadManager.Request {
    return DownloadManager.Request(Uri.parse(url)).setTitle(title)
        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
        .setAllowedOverMetered(true)
        .setAllowedOverRoaming(true)
        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        .setDestinationUri(Uri.fromFile(file))
}

fun createFile(nameFile:String, context:Context):File {
    return  File(context.getExternalFilesDir(null),nameFile)
}








