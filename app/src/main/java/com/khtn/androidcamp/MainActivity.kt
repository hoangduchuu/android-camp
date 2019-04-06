package com.khtn.androidcamp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.squareup.picasso.Picasso
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
        startActivityForResult(intent, REQUEST_SETTING_CODE)
    }

    companion object {
        const val REQUEST_SETTING_CODE = 1099;
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_SETTING_CODE && resultCode == Activity.RESULT_OK) {
            val backgroundUrl = data?.getStringExtra("background")
            val isDarkMode = data?.getBooleanExtra("isDarkMode", false)
            handleSettingValues(backgroundUrl,isDarkMode)
        }
    }

    private fun handleSettingValues(backgroundUrl: String?, darkMode: Boolean?) {
        // do something with data
        Picasso.get().load(backgroundUrl).into(ivLogo)
    }
}




