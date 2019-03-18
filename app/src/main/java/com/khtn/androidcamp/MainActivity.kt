package com.khtn.androidcamp

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

@SuppressLint("SetTextI18n")


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)   // Khai báo layout


        tvUserName.text = "Android KHTN"
        btnLogin.text = "Register"


        btnLogin.setOnClickListener {
            // Khai báo listener khi click button
            Log.d("YourTagName", "Clicked Button Login")
        }
    }
}




