package com.khtn.androidcamp

import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.demo_databse_fragment.*


/**
 * Created by Huu Hoang on 4/25/19.
 */
class DemoDatabaseFragment : BaseFragment() {
    val MSSV : String = "08201000"
    interface Listener {
        fun openLoginScreen()
    }

    lateinit var mListener: Listener

    lateinit var firebaseDAtabase: FirebaseDatabase

    lateinit var reference: DatabaseReference


    override fun getTagName(): String {
        return DemoDatabaseFragment::class.java.simpleName
    }

    override fun inflateView(): Int {
        return R.layout.demo_databse_fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initFirebase()

        initViews()

        registerListener()

    }

    private fun initFirebase() {
        firebaseDAtabase = FirebaseDatabase.getInstance()
        reference = firebaseDAtabase.reference
    }

    private fun initViews() {
        tvInfo.setOnClickListener {
            mListener.openLoginScreen()
        }

        btUpdate.setOnClickListener {
            val name = edtInput.text.toString()
            reference.child("name").child(MSSV).setValue(name)
        }

    }

    private fun registerListener() {
        reference.child("name").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Log.e(getTagName(), p0.message)
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()) {
                    if (p0.hasChildren()){
                        for (p in p0.children){
                            if (p.key == MSSV) {
                                tvInfo.text = p.value as String
                            } else {
                                tvInfo.text = "Chưa có MSSV trên Firebase"
                            }
                        }
                    }

                }
            }

        })
    }


    fun setListener(listener: Listener) {
        mListener = listener
    }


}