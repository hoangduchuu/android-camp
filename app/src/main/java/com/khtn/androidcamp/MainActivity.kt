package com.khtn.androidcamp

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
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
            showDialog()
        }


    }

    private fun showDialog() {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("My Title")
            .setMessage("My Long long long long messages")
            .setPositiveButton("OK", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    // do Some thing
                }

            })
            .setNegativeButton("Cancel", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    dialog?.dismiss()
                }

            })

        val myDialog = builder.create();
        myDialog.show()


    }
}




