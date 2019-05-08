package com.khtn.androidcamp

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.khtn.androidcamp.ROOM.AppDatabase
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_add_student.*
import kotlin.collections.ArrayList


class AddStudentActivity : AppCompatActivity() {

    val spinnerData = ArrayList<Pair<String, Int>>()

    var student = Student()

    var teacherAvatar = -1

    lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        db = AppDatabase.invoke(this) // get Room database instance

        setupSpinner()
        handleSubmitData()

    }

    private fun handleSubmitData() {
        btnSubmit.setOnClickListener {
            // FIXME:  validate empty input value
            student.name = tvName.text.toString()
            student.classz = tvClass.text.toString()
            student.avatarOfTeacher = teacherAvatar

            val studentDAO = db.studentDAO() // get DAO instance
            val id = studentDAO.insert(student) // insert our student object to ROOM database

            student.id = id.toInt()

            /**
             * send inserted student-object to main screen when insert database successful
             */
            val intent = Intent()
            intent.putExtra(STUDENT_OBJECT_KEY, student)
            setResult(Activity.RESULT_OK, intent);
            finish()
        }
    }

    private fun setupSpinner() {
        spinnerData.add(Pair("[---Select Coach--]", R.drawable.student_place_holder))
        spinnerData.add(Pair("Tien Dung", R.drawable.buitiendung))
        spinnerData.add(Pair("Cong Phuong", R.drawable.congphuong))
        spinnerData.add(Pair("Anh Duc", R.drawable.anhduc))
        spinnerData.add(Pair("Cong Vinh", R.drawable.conhvinh))
        spinnerData.add(Pair("Dinh Manh Ninh", R.drawable.dmninh))
        spinnerData.add(Pair("Duy Manh", R.drawable.duymanh))
        spinnerData.add(Pair("Huynh Duc", R.drawable.huynhduc))
        spinnerData.add(Pair("Quang Hai", R.drawable.quanghai))
        spinnerData.add(Pair("Van Toan", R.drawable.vantoan))
        spinnerData.add(Pair("Vinh Rau", R.drawable.vinhrau)) // clone the data

        val spinnerAdapter =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerData.map { it.first })
        spiner.adapter = spinnerAdapter
        spiner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                teacherAvatar = spinnerData[position].second
                Glide.with(this@AddStudentActivity).load(spinnerData[position].second).into(ivFan)
            }
        }
    }

}
