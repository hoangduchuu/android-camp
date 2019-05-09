package com.khtn.androidcamp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import kotlinx.android.synthetic.main.activity_main.*

@SuppressLint("SetTextI18n")


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnChange.setOnClickListener {
            // DO something here
            gotoSettingScreen()
        }

        getBackground()
    }

    private fun gotoSettingScreen() {
        val intent = Intent(this, SettingBackgroundActivity::class.java)
        startActivityForResult(intent, REQUEST_CHANGE_BACKGROUND)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CHANGE_BACKGROUND && resultCode == Activity.RESULT_OK) {
            getBackground()
        }
    }


    @SuppressLint("CheckResult")
    private fun getBackground() {

        val backgroundUrl = SharedPreferencesHelper.readString(BACKGROUND_KEY)

        Glide.with(this)
            .asBitmap()
            .load(backgroundUrl).into(object : CustomTarget<Bitmap>() {
                override fun onLoadCleared(placeholder: Drawable?) {}

                override fun onResourceReady(bitmap: Bitmap, transition: Transition<in Bitmap>?) {
                    val bit = BitmapDrawable(bitmap)
                    lnBg.background = bit
                }
            })
    }


}




