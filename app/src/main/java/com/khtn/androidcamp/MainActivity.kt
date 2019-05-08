package com.khtn.androidcamp

import android.annotation.SuppressLint
import android.arch.persistence.room.Room
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.khtn.androidcamp.ROOM.AppDatabase
import com.khtn.androidcamp.ROOM.StudentDAO
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.schedule
import android.app.Activity


@SuppressLint("SetTextI18n")


class MainActivity : AppCompatActivity() {

    var students: ArrayList<Student> = ArrayList()
    lateinit var studentAdapter: StudentAdapter
    lateinit var dao: StudentDAO
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRoomDatabase()

        setupRecyclerView()

        getStudents()


        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, AddStudentActivity::class.java)
            startActivityForResult(intent, CODE_ADD_NEW_STUDENT)
        }

    }

    private fun initRoomDatabase() {
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, DATABASE_NAME
        ).allowMainThreadQueries()
            .build()
        dao = db.studentDAO()
    }

    /**
     * setup layout manager and recyclerview
     */
    private fun setupRecyclerView() {
        rvStudents.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?

        studentAdapter = StudentAdapter(students, this)

        rvStudents.adapter = studentAdapter

        studentAdapter.setListener(studentItemCLickListener)
    }

    private val studentItemCLickListener = object : StudentItemCLickListener {
        override fun onItemCLicked(position: Int) {

            val intent = Intent(this@MainActivity, ProfileActivity::class.java)
            intent.putExtra(STUDENT_NAME_KEY, students[position].name)
            intent.putExtra(STUDENT_AVATAR_KEY, students[position].avatarOfTeacher)
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
        dao.delete(students[position]) // remove from Room database  //

        students.removeAt(position) // remove student list on RAM

        studentAdapter.notifyItemRemoved(position) // notify data change
        Timer(false).schedule(500) {
            runOnUiThread {
                studentAdapter.setData(students)
                studentAdapter.notifyDataSetChanged()
            }
        }
    }


    private fun getStudents() {
        val students = dao.getAll() // get Students from ROOM database

        this.students.addAll(students) // add to student list

        studentAdapter.notifyDataSetChanged() // notify data changed
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CODE_ADD_NEW_STUDENT && resultCode == Activity.RESULT_OK) {
            val newStudentAdded = data?.extras?.getParcelable(STUDENT_OBJECT_KEY) as Student
            handleOnNewStudentAdded(newStudentAdded)
        }
    }

    /**
     * append new data to student list and notify data change
     */
    private fun handleOnNewStudentAdded(student: Student) {
        studentAdapter.appendData(student)
    }

}




