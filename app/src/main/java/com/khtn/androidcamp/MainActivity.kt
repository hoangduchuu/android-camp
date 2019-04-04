package com.khtn.androidcamp

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.widget.CompoundButton
import kotlinx.android.synthetic.main.activity_main.*

@SuppressLint("SetTextI18n")


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)   // Khai báo layout


        swTv.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // turn on TV
                Log.e("KHTH", "turn on TV")
            } else {
                Log.e("KHTH", "turn OFF TV")
            }
        }



    }


}




