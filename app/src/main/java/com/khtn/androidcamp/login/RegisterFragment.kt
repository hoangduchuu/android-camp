package com.khtn.androidcamp.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.khtn.androidcamp.BaseFragment
import com.khtn.androidcamp.R
import com.khtn.androidcamp.models.User
import com.khtn.androidcamp.profile.ProfileActivity
import kotlinx.android.synthetic.main.register_fragment.*

/**
 * Created by Huu Hoang on 4/25/19.
 */
class RegisterFragment : BaseFragment() {
    interface Listener {
        fun openLoginScreen()
    }

    lateinit var mAuth: FirebaseAuth

    lateinit var mDB: FirebaseDatabase

    lateinit var dbRef: DatabaseReference

    lateinit var mListener: Listener

    override fun getTagName(): String {
        return RegisterFragment::class.java.simpleName
    }

    override fun inflateView(): Int {
        return R.layout.register_fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()

        initFirebase()
    }

    private fun initViews() {
        tvLogin.setOnClickListener {
            mListener.openLoginScreen()
        }
        btnRegister.setOnClickListener {
            goRegister()
        }
    }


    private fun goRegister() {
        val email = edtMail.text.toString().trim()
        val password = edPassword.text.toString().trim()
        val name = edtName.text.toString().trim()
        val userName = edtUserName.text.toString().trim()

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnFailureListener {
                Toast.makeText(context, "Register Failed: ${it.localizedMessage}", Toast.LENGTH_SHORT).show()
            }.addOnSuccessListener {
                Toast.makeText(context, "Register success: ${it.user.email}", Toast.LENGTH_SHORT).show()
                submitUserInfoToFirebase(name, userName,email)
                startActivity(Intent(activity, ProfileActivity::class.java))
            }.addOnCanceledListener {
                Toast.makeText(context, "Register Canceled ", Toast.LENGTH_SHORT).show()
            }
    }

    private fun submitUserInfoToFirebase(name: String, userName: String, email: String) {

        val user = User(name, userName, mAuth.currentUser?.uid!!, email)

        dbRef.child("Users").child( mAuth.currentUser?.uid!!)
            .setValue(user)

    }

    private fun initFirebase() {
        mAuth = FirebaseAuth.getInstance()
        mDB = FirebaseDatabase.getInstance()
        dbRef = mDB.reference
    }

    fun setListener(listener: Listener) {
        mListener = listener
    }


}