package com.khtn.androidcamp.updateprofile

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.khtn.androidcamp.R
import kotlinx.android.synthetic.main.activity_update_profile.*

class UpdateProfileActivity : AppCompatActivity() {


    lateinit var mAuth: FirebaseAuth
    lateinit var databaseRef: DatabaseReference
    lateinit var firebaseDatabase: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_profile)

        initView()

        initFirebase()
    }

    private fun initView() {

        btnUpdate.setOnClickListener {
            doUpdateProfile()
        }
    }

    private fun doUpdateProfile() {
        val mail = edtmail.text.toString().trim()
        val phone = edtPhone.text.toString().trim()

        databaseRef
            .child("Users")
            .child(mAuth.currentUser?.uid!!)
            .child("email")
            .setValue(mail)
        databaseRef
            .child("Users")
            .child(mAuth.currentUser?.uid!!)
            .child("phone")
            .setValue(phone)

    }


    private fun initFirebase() {
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseRef = firebaseDatabase.reference
        mAuth = FirebaseAuth.getInstance()
    }
}
