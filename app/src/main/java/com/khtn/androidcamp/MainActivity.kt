package com.khtn.androidcamp

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

@SuppressLint("SetTextI18n")


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLogin.setOnClickListener {
            // DO something here
            gotoSettingScreen()
        }

    }

    private fun gotoSettingScreen() {
        val intent = Intent(this, SettingBackgroundActivity::class.java)
        intent.putExtra("age", 12)
        intent.putExtra("name", "I'm Android")
        intent.putExtra("isMale", true)
        startActivity(intent)
    }

}




