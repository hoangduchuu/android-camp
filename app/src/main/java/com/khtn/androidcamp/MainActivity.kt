package com.khtn.androidcamp

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.widget.DividerItemDecoration
import android.widget.LinearLayout
import android.widget.LinearLayout.HORIZONTAL

@SuppressLint("SetTextI18n")


class MainActivity : AppCompatActivity() {

    var students: ArrayList<Student> = ArrayList()
    lateinit var studentAdapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // init clone student data
        addStudents()

        // setup layout manager and recyclerview
        rvStudents.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?

        studentAdapter = StudentAdapter(students, this)

        rvStudents.adapter = studentAdapter

        studentAdapter.setListener(studentItemCLickListener)

    }

    private val studentItemCLickListener = object : StudentItemCLickListener {
        override fun onItemCLicked(position: Int) {
            // copy một dòng khi click
            students.add(position+1,Student(
                students[position].name + " Copy ",
                students[position].classz + " Copy " ))
            studentAdapter.setData(students)
        }

        override fun onItemLongCLicked(position: Int) {
            // xoa 1 dong khi long click
            students.removeAt(position)
            studentAdapter.setData(students)

        }

    }

    private fun addStudents() {
        students.add(Student("Nguyen QUang Hai", " Ha Noi FC"))
        students.add(Student("Bui Tien Dung", " Ha Noi FC"))
        students.add(Student("Duy Manh", " Ha Noi FC"))
        students.add(Student("Cong Phuong", " HAGL FC"))
        students.add(Student("Van Toan", " HAGL FC"))
        students.add(Student("Huynh Duc", " Da Nang FC"))
        students.add(Student("Vinh Rau", " FAP TV"))
        students.add(Student("Cong Vinh", " Nghe An FC"))
        students.add(Student("Anh Duc", " Binh Duong FC"))
        students.add(Student("Dinh Manh Ninh", "M4U"))
    }
}




