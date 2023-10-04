package com.example.coctails.utils

import android.content.Context
import android.media.MediaMetadataRetriever
import android.net.Uri
import androidx.core.net.toUri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

suspend fun extractUri(uri: Uri?, context: Context): String {
    if (uri == null) {
        return ""
    }

    try {
        context.contentResolver.openInputStream(uri)?.use { inputStream ->
            val bytes = inputStream.readBytes()

            val title = uri.lastPathSegment ?: "unknown"
            val uniqueId = System.currentTimeMillis()
            val fileName = "$title-$uniqueId"
            val file = File(context.filesDir, fileName)

            withContext(Dispatchers.IO) {
                FileOutputStream(file).use {
                    it.write(bytes)
                }
            }
            return file.toUri().toString()
        }
    } catch (e: Exception) {
        e.printStackTrace()
        return ""
    }
    return ""
}