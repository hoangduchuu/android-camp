package com.khtn.androidcamp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        getAndPutData()
    }

    private fun getAndPutData() {
        val data = intent.extras

        if (data != null) {
            val name = data.getString(STUDENT_NAME_KEY)
            val classz = data.getString(STUDENT_CLUB_KEY)
            val avatar = data.getInt(STUDENT_AVATAR_KEY)


            tvClass.text = name
            tvName.text = name
            tvName02.text = name
            tvClass.text = classz
            Glide.with(this)
                .load( avatar)
                .centerCrop()
                .placeholder(R.drawable.student_place_holder)
                .into(ivHeader)

        }
    }
}
