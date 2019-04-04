package com.khtn.androidcamp

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import kotlinx.android.synthetic.main.activity_main.*


@SuppressLint("SetTextI18n")

class MainActivity : AppCompatActivity() {

    private lateinit var loading: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)   // Khai báo layout
        loading = pbLoading

        downloadSlide()

    }

    private fun downloadSlide() {
        DownloadTask().execute()
    }

    inner class DownloadTask : AsyncTask<Void, Void, String>() {
        override fun doInBackground(vararg params: Void?): String {
            pbLoading.visibility = View.VISIBLE // show loading

            Thread.sleep(3000) // clone our job in 3s
            return ""
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            // when finished

            pbLoading.visibility = View.GONE // hide loading

        }
    }


}






