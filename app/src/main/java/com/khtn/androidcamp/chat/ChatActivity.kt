package com.khtn.androidcamp.chat

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

import com.khtn.androidcamp.R
import com.khtn.androidcamp.login.LoginActivity
import com.khtn.androidcamp.models.User
import com.khtn.androidcamp.profile.ProfileActivity
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog
import kotlinx.android.synthetic.main.activity_chat2.*

import java.util.Calendar

class ChatActivity : AppCompatActivity() {
    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val rootDB = firebaseDatabase.reference
    lateinit var mAuth: FirebaseAuth

    private var adapter: ChatAdapter? = null

    private val onSubmitChatListener = View.OnClickListener {
        //todo push
        val msg = editText!!.text.toString().trim { it <= ' ' }
        val timestample = Calendar.getInstance().timeInMillis
        val message = Message(msg, timestample)
        rootDB.child("Messages").push().setValue(message)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat2)

        initViews()

        setupRecyclerView()

        registerListener()

        checkUser()
    }

    private fun checkUser() {
        mAuth = FirebaseAuth.getInstance()
        val user = FirebaseAuth.getInstance().currentUser
        if (user == null) {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun initViews() {
        btnSubmitChat.setOnClickListener(onSubmitChatListener)
    }

    private fun setupRecyclerView() {
        val lm = LinearLayoutManager(this)
        rv?.layoutManager = lm
        rv?.setHasFixedSize(true)
        adapter = ChatAdapter(this)
        rv?.adapter = adapter
    }

    private fun registerListener() {
        rootDB.child("Messages").addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
                if (dataSnapshot.exists()) {
                    val m = dataSnapshot.getValue(Message::class.java)
                    if (m != null) {
                        Log.e(TAG, ">>>>> 11$s")
                        adapter!!.addData(m)
                        rv?.smoothScrollToPosition(adapter!!.itemCount)
                        editText?.setText("")
                    }
                }
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {

            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {

            }

            override fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {

            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
    }

    companion object {
        private val TAG = ChatActivity::class.java.simpleName
    }
}
