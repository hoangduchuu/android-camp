package com.khtn.androidcamp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.khtn.androidcamp.ROOM.AppDatabase
import kotlinx.android.synthetic.main.activity_update.*

class UpdateActivity : AppCompatActivity() {
    lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        db = AppDatabase.invoke(this)


        btnSave.setOnClickListener {
            handleOnBtnSaveClicked()
        }
    }

    private fun handleOnBtnSaveClicked() {
       // TODO update and put sutdent object backto main screen

//        db.studentDAO().update()

    }
}
