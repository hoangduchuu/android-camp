package com.khtn.androidcamp

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException
import java.util.*

@SuppressLint("SetTextI18n")


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnGetData.setOnClickListener {
            getData("${edtUserID.text.trim()}_${Calendar.getInstance().timeInMillis}")
        }
    }

    private fun getData(teacherName: String) {
        pbLoading.visibility = View.VISIBLE


        val client = OkHttpClient()

        val teacher = Teacher(teacherName, 1000, 12,id = Calendar.getInstance().timeInMillis)
        val json = Gson().toJson(teacher)


        val body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json)
        val request = Request.Builder()
            .url("http://dummy.restapiexample.com/api/v1/create")
            .post(body)
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
//                    val user = Gson().fromJson(json, User::class.java)
                    runOnUiThread {
                        tvData.text = json.toString()
                        pbLoading.visibility = View.GONE
                    }
                }

            })
    }

    private fun getJson(): String {
        return assets.open("data.json").bufferedReader()
            .use { it.readText() } // read read offline json from assets folder

    }
}




