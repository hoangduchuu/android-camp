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


class MainActivity : AppCompatActivity(), MyListener {
    lateinit var adapter: StudentAdapter
    lateinit var mDialog: SweetAlertDialog

    private var students = ArrayList<Student>()

    private var selectedItem = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setupRV()

        initViews();
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

        // TODO INSERT NEW STUDENT TO ROOM

        students.add(Student(edtName.text.toString(), edtAge.text.toString().toInt()))
        adapter.notifyItemInserted(students.size - 1)
        rvInfo.scrollToPosition(students.size - 1)

        hideKeyboard()
    }

    /**
     * REMOVE ONE
     */
    private fun handleRemoveItemConfirmed(position: Int) {
        students.removeAt(position)

        // TODO REMOVE  STUDENT FROM ROOM

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

        // TODO GetLIST STUDENT FROM ROOM

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

        // TODO UPDATE SELECTED ITEM TO ROOM

        students[selectedItem] = Student(edtName.text.toString(), edtAge.text.toString().toInt())

        adapter.notifyDataSetChanged()

        hideKeyboard()
    }


}




