package com.khtn.androidcamp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.util.Log
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var currentPhotoPath: String
    val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
        btnCapture.setOnClickListener { takePicture() }
        btnSaveImage.setOnClickListener {}
    }


    private fun takePicture() {
        val phoToFile: File?

        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        intent.resolveActivity(packageManager)
        try {
            phoToFile = createImageFile()
            val photoURI = FileProvider.getUriForFile(this, "com.khtn.androidcamp", phoToFile)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
        } catch (ex: IOException) {
            Log.e(TAG, ex.localizedMessage)
        }
    }

    /**
     * Tạo File name dựa vào thời gian hiện tại
     */
    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File = this.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val newFIle = File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir
        )
        currentPhotoPath = newFIle.absolutePath

        return newFIle
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

            // TODO Xử lý kết quả trả về
            val file = File(currentPhotoPath)
            // /storage/emulated/0/Android/data/com.khtn.androidcamp/files/Pictures/JPEG_20190516_175446_4830103794032036545.jpg
            Log.e(TAG, file.absolutePath)
            Glide.with(this).load(file).into(imageView)
        }
    }




}




