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
        setContentView(R.layout.activity_main)

        btnLogin.setOnClickListener {
            // DO something here
            showDialog()
        }


    }

    private fun showDialog() {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("My Title")
            .setMessage("My Long long long long messages")
            .setPositiveButton("OK") { _, _ ->
                // do Some thing
            }
            .setNegativeButton("Cancel"
            ) { dialog, _ -> dialog?.dismiss() }

        val myDialog = builder.create();
        myDialog.show()


    }
}




