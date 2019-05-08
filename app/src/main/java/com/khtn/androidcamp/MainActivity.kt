package com.khtn.androidcamp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

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


    private fun getBackground() {

        val backgroundUrl = SharedPreferencesHelper.readString(BACKGROUND_KEY)

        // use Picasso library load image from internet
        Picasso.get().load(backgroundUrl).into(object : Target {
            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
            }

            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                val bit = BitmapDrawable(bitmap)
                lnBg.background = bit
            }

        })
    }


}




