package com.khtn.androidcamp

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
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

            val intent = Intent(this@MainActivity,ProfileActivity::class.java)
            intent.putExtra(STUDENT_NAME_KEY, students[position].name)
            intent.putExtra(STUDENT_AVATAR_KEY, students[position].avatar)
            intent.putExtra(STUDENT_CLUB_KEY, students[position].classz)
            startActivity(intent)

        }
        override fun onItemLongCLicked(position: Int) {
            
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setTitle("Confirmation")
                .setMessage("Remove ${students[position].name} ?")
                .setPositiveButton("OK") { _, _ ->
                    removeItem(position)
                }
                .setNegativeButton(
                    "Cancel"
                ) { dialog, _ -> dialog?.dismiss() }

            val myDialog = builder.create();
            myDialog.show()
        }
    }

    private fun removeItem(position: Int) {
        students.removeAt(position)
        studentAdapter.setData(students)
    }

    private fun addStudents() {
        students.add(Student("Nguyen Quang Hai", " Ha Noi FC", R.drawable.quanghai))
        students.add(Student("Bui Tien Dung", " Ha Noi FC", R.drawable.buitiendung))
        students.add(Student("Duy Manh", " Ha Noi FC", R.drawable.duymanh))
        students.add(Student("Cong Phuong", " HAGL FC", R.drawable.congphuong))
        students.add(Student("Van Toan", " HAGL FC", R.drawable.vantoan))
        students.add(Student("Huynh Duc", " Da Nang FC", R.drawable.huynhduc))
        students.add(Student("Vinh Rau", " FAP TV", R.drawable.vinhrau))
        students.add(Student("Cong Vinh", " Nghe An FC", R.drawable.conhvinh))
        students.add(Student("Anh Duc", " Binh Duong FC",R.drawable.anhduc))
        students.add(Student("Dinh Manh Ninh", "M4U", R.drawable.dmninh))
    }
}




