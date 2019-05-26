package com.khtn.androidcamp

import android.os.AsyncTask
import android.os.Environment
import android.util.Log
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

/**
 * Created by Huu Hoang on 2019-05-25.
 */

class SaveImageTask() : AsyncTask<String, Void, Boolean>() {
    override fun doInBackground(vararg params: String?): Boolean? {
        return params[0]?.let { saveFileImage(it) }
    }

    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun onPostExecute(result: Boolean?) {
        super.onPostExecute(result)
    }
    val TAG = SaveImageTask::class.java.simpleName
    fun saveFileImage(location: String) : Boolean{
        var ins = FileInputStream(File(location))
        val savedImage =
            File(Environment.getExternalStorageDirectory(), "/image_${Calendar.getInstance().timeInMillis}.jpg")
        Log.e(TAG, savedImage.path)
        try {
            var ous = FileOutputStream(savedImage)
            val buffer = ByteArray(8 * 1024)
            var bytesRead: Int
            bytesRead = ins.read(buffer)
            while (bytesRead != -1) {
                ous.write(buffer, 0, bytesRead)
                bytesRead = ins.read(buffer)
            }
            ins.close()
            ous.close()
        } catch (e : IOException){
            e.printStackTrace()
            return false
        }
        return true
    }
}
