package com.khtn.androidcamp

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.khtn.androidcamp.room.Student
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.schedule

@SuppressLint("SetTextI18n")

//reject em đi a.Hữu

class MainActivity : AppCompatActivity(), MyListener {
    lateinit var adapter: StudentAdapter
    lateinit var mDialog: SweetAlertDialog

    private var students = ArrayList<Student>()

    private var selectedItem = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setupRV()

        initViews()
    }

    private fun initViews() {
        btnSubmit.setOnClickListener {
            handleSubmitNewStudent()
        }
        btnUpdate.setOnClickListener {
            handleUpdateButtonClicked()
        }
    }


    private fun setupRV() {
        students = cloneData()
        adapter = StudentAdapter(this, students)
        // setup layout manager and recyclerview
        rvInfo.layoutManager = LinearLayoutManager(this)


        rvInfo.adapter = adapter

        adapter.setListener(this)
    }


    override fun onDeleteButtonClicked(position: Int) {
        Log.e("HHH: ", "delete $position")
        mDialog = SweetAlertDialog(this@MainActivity, SweetAlertDialog.BUTTON_NEGATIVE)
        mDialog.setTitleText("Are you sure?")
            .setContentText("Won't be able to recover this file!")
            .setCancelText("No,cancel plx!")
            .setConfirmText("Yes,delete it!")
            .setCancelClickListener {
                mDialog.dismissWithAnimation()
            }
            .setConfirmClickListener {
                handleRemoveItemConfirmed(position)
                mDialog.dismissWithAnimation()
            }
        mDialog.setCancelable(false)
        mDialog.show()
    }

    override fun onEditButtonClicked(position: Int) {
        selectedItem = position
    }

    /**
     * ADD NEW
     */
    private fun handleSubmitNewStudent() {
        val name = edtName.text.toString()
        val age: Int = edtAge.text.toString().toInt()
        // TODO INSERT NEW STUDENT TO ROOM

        students.add(Student(name, age))
        adapter.notifyItemInserted(students.size - 1)
        rvInfo.scrollToPosition(students.size - 1)

        hideKeyboard()
    }

    /**
     * REMOVE ONE
     */
    private fun handleRemoveItemConfirmed(position: Int) {
        // TODO REMOVE  STUDENT FROM ROOM

        students.removeAt(position)
        adapter.notifyItemRemoved(position)
        Timer().schedule(400) {
            runOnUiThread {
                adapter.setData(students)
            }
        }
    }

    /**
     * READ List
     */
    private fun cloneData(): ArrayList<Student> {
        // TODO GET LIST STUDENT FROM ROOM and DELETE BELOW CODE

        val students = ArrayList<Student>()
        for (x in 0..18) {
            students.add(Student("Nguyen Van $x", x))
        }
        return students
    }

    /**
     * UPDATE SELECTED ITEM
     */
    private fun handleUpdateButtonClicked() {
        val name = edtName.text.toString()
        val age: Int = edtAge.text.toString().toInt()
        // TODO UPDATE SELECTED ITEM TO ROOM

        students[selectedItem] = Student(name, age)

        adapter.notifyDataSetChanged()

        hideKeyboard()
    }


}




