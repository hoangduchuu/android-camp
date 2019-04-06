package com.khtn.androidcamp

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_setting_background.*

class SettingBackgroundActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting_background)

        val data = intent.extras

        if (data != null) {
            val age = data.getInt("age")
            val name = data.getString("name")
            val isMale = data.getBoolean("isMale")

            val info = "- Name: $name \n- Age: $age \n- Gender: ${getGender(isMale)}"
            tvUserInfo.text = info
        }


        btnBack.setOnClickListener {
            handleOnBackScreen()
        }
    }

    private fun handleOnBackScreen() {
        val url = "https://s.vnecdn.net/vnexpress/restruct/i/v47/graphics/img_logo_vne_web.gif"
        val intent = Intent()
        intent.putExtra("background", url);
        intent.putExtra("isDarkMode", false)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }




    private fun getGender(isMale: Boolean): String {
        return when {
            isMale -> "Male"
            else -> "FeMale"
        }
    }
}
