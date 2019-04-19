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
            val student = data.getParcelable(STUDENT_KEY) as Student


            tvClass.text = student.classz
            tvName.text = student.name
            tvName02.text = student.name
            tvClass.text = student.classz
            Glide.with(this)
                .load( drawableByName(student.avatar))
                .centerCrop()
                .placeholder(R.drawable.student_place_holder)
                .into(ivHeader)

        }
    }
}
