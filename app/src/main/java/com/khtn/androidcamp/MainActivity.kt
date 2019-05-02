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
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

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
            var category = Category(categoryId = 1, categoryName = "MacBook")
            var item = Item(imageId = 2, price = 30.0, title = "MacBook Pro", category = category)

            val intent = Intent(this@MainActivity,ProfileActivity::class.java)
            intent.putExtra(STUDENT_KEY, students[position])
            intent.putExtra(CONSTANT_KEY, item)
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
        // read json
        val json = assets.open("students.json").bufferedReader()
            .use { it.readText() } // read read offline json from assets folder

        // convert json to POJO add add to the collection=
        val collectionType = object : TypeToken<Collection<Student>>() {}.type

        students = Gson().fromJson(json, collectionType)
    }
}




