package com.khtn.androidcamp.profile

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.khtn.androidcamp.R
import com.khtn.androidcamp.models.User
import com.khtn.androidcamp.updateprofile.UpdateProfileActivity
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {
    lateinit var mAuth: FirebaseAuth
    lateinit var databaseRef: DatabaseReference
    lateinit var firebaseDatabase: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        initView()

        initFirebase()

        registerFirebase()
    }

    private fun registerFirebase() {
        databaseRef.child("Users")
            .child(mAuth.currentUser!!.uid)
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val user = snapshot.getValue(User::class.java) as User
                        tvFullName.text = user.fullName
                        tvUserName.text = user.userName
                        if (user.phone?.toInt() == 0 || user.phone == null) {
                            val dialog = SweetAlertDialog(this@ProfileActivity, SweetAlertDialog.ERROR_TYPE)
                            dialog.titleText = "Update Phone"
                            dialog.contentText = "SET PHONE"
                            dialog.show()
                        }
                        Toast.makeText(applicationContext, "${user.email}", Toast.LENGTH_SHORT).show()
                    }
                }

            })
    }

    private fun initFirebase() {
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseRef = firebaseDatabase.reference
        mAuth = FirebaseAuth.getInstance()
    }

    private fun initView() {
        btnUpdate.setOnClickListener {
            startActivity(Intent(this, UpdateProfileActivity::class.java))
        }
        btnUpdate2.setOnClickListener {
            startActivity(Intent(this, UpdateProfileActivity::class.java))
        }
    }
}
