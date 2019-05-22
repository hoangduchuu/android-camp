package com.khtn.androidcamp.chat

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View

import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.khtn.androidcamp.R
import kotlinx.android.synthetic.main.activity_chat2.*

import java.util.Calendar

class ChatActivity : AppCompatActivity() {
    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val rootDB = firebaseDatabase.reference

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
