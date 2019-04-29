package com.khtn.androidcamp

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

@SuppressLint("SetTextI18n")


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnGetData.setOnClickListener {
            getData(edtUserID.text.toString())
        }
    }

    private fun getData(id: String) {
        pbLoading.visibility = View.VISIBLE
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://jsonplaceholder.typicode.com/todos/$id")
            .build()

        client.newCall(request)
            .enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    runOnUiThread {
                        tvData.text = "ERROR: ${e.localizedMessage}"
                        pbLoading.visibility = View.GONE

                    }
                }

                override fun onResponse(call: Call, response: Response) {
                    val json = response.body()!!.string()
                    val user = Gson().fromJson(json, User::class.java)
                    runOnUiThread {
                        tvData.text = user.toString()
                        pbLoading.visibility = View.GONE
                    }
                }

            })
    }
}




