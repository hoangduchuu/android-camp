package com.khtn.androidcamp.chat

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

import com.khtn.androidcamp.R
import com.khtn.androidcamp.login.LoginActivity
import com.khtn.androidcamp.models.User
import com.khtn.androidcamp.updateprofile.UpdateProfileActivity
import kotlinx.android.synthetic.main.activity_chat2.*

import java.util.Calendar

class ChatActivity : AppCompatActivity() {
    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val rootDB = firebaseDatabase.reference
    lateinit var mAuth: FirebaseAuth

    private var adapter: ChatAdapter? = null

    lateinit var headerView: View

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
        }else{
            getInfoUser()
        }
    }

    private fun initViews() {
        btnSubmitChat.setOnClickListener(onSubmitChatListener)
        headerView = nav_view.inflateHeaderView(R.layout.nav_header)
    }

    private fun setupRecyclerView() {
        val lm = LinearLayoutManager(this)
        rv?.layoutManager = lm
        rv?.setHasFixedSize(true)
        adapter = ChatAdapter(this)
        rv?.adapter = adapter
    }

    private fun registerListener() {
        mAuth = FirebaseAuth.getInstance()

        // data chat
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

    private fun getInfoUser(){
        // data User INFO
        rootDB.child("Users").child(mAuth.currentUser?.uid!!)
            .addValueEventListener(
                object : ValueEventListener {
                    override fun onCancelled(e: DatabaseError) {

                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        if (p0.exists()) {
                            val user = p0.getValue(User::class.java) as User
                            setUserInfo(user)
                        }
                    }

                }
            )
    }

    private fun setUserInfo(user: User) {
        val tvFullName = headerView.findViewById<TextView>(R.id.tvFullName)
        val tvPhone = headerView.findViewById<TextView?>(R.id.tvPhone)
        val tvEmail = headerView.findViewById<TextView>(R.id.tvEmail)
        tvEmail.setOnClickListener(
            object : View.OnClickListener {
                override fun onClick(v: View?) {
                    Toast.makeText(this@ChatActivity, "abcdef", Toast.LENGTH_SHORT).show()
                }

            }
        )

        tvFullName.text = user.fullName
        tvPhone?.text = user.phone.toString()
        tvEmail.text = user.email
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        val inflater = menuInflater
        inflater.inflate(com.khtn.androidcamp.R.menu.drawer_view, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        super.onOptionsItemSelected(item)
        when (item?.itemId) {
            R.id.nav_menu_edit -> {
                startActivity(Intent(this@ChatActivity, UpdateProfileActivity::class.java))
                return true
            }
            else -> {
                return true
            }
        }

    }

    companion object {
        private val TAG = ChatActivity::class.java.simpleName
    }
}
