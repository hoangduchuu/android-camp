package com.khtn.androidcamp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.register_fragment.*

/**
 * Created by Huu Hoang on 4/25/19.
 */
class RegisterFragment : BaseFragment() {
    interface Listener {
        fun openLoginScreen()
    }

    lateinit var mAuth: FirebaseAuth

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
            doLogin()
        }
    }

    private fun doLogin() {
        val email = edtMail.text.toString().trim()
        val password = edPassword.text.toString().trim()

        mAuth.createUserWithEmailAndPassword(email,password)
            .addOnFailureListener {
                Toast.makeText(context,"Register Failed: ${it.localizedMessage}", Toast.LENGTH_SHORT).show()
            }.addOnSuccessListener {
                Toast.makeText(context,"Register success: ${it.user.email}", Toast.LENGTH_SHORT).show()
            }.addOnCanceledListener {
                Toast.makeText(context,"Register Canceled ", Toast.LENGTH_SHORT).show()
            }
    }

    private fun initFirebase() {
        mAuth = FirebaseAuth.getInstance()
    }

    fun setListener(listener: Listener) {
        mListener = listener
    }


}