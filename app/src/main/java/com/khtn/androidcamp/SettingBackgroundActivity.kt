package com.khtn.androidcamp

import android.annotation.SuppressLint
import android.app.PendingIntent.getActivity
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_setting_background.*
import android.content.SharedPreferences
import android.app.Activity
import android.content.Intent


class SettingBackgroundActivity : AppCompatActivity() {

    val backgroundUrls = ArrayList<String>()

    var selectedBackground = ""

    var backgroundIndex = 0

    lateinit var sharedPreferences: SharedPreferences

    lateinit var prefEditor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting_background)

        initSharePreference()

        setupSpinner()

        btnSubmitBackground.setOnClickListener {
            submitBackgroundUrlToSharedPref()
        }

    }

    @SuppressLint("CommitPrefEdits")
    private fun initSharePreference() {
        sharedPreferences = getSharedPreferences(PREFERCENCES_NAME, Context.MODE_PRIVATE)

        prefEditor = sharedPreferences.edit()

        getCurrentIndex() // get current index

    }

    private fun submitBackgroundUrlToSharedPref() {

        prefEditor.putString(BACKGROUND_KEY, selectedBackground)

        prefEditor.putInt(BACKGROUND_INDEX, backgroundIndex)

        prefEditor.apply()


        /**
         * quay dau lai la bo
         */

        val intent = Intent()
        setResult(Activity.RESULT_OK, intent);
        finish()
    }

    private fun setupSpinner() {
        for (x in 1..100) run {
            backgroundUrls.add("https://picsum.photos/id/$x/600/900")
        } // clone the data


        val spinnerAdapter =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, backgroundUrls)
        spinner.adapter = spinnerAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Picasso.get().load(backgroundUrls[position]).into(ivBackground) // load preview
                selectedBackground = backgroundUrls[position] // set to global variable
                backgroundIndex = position
            }
        }

        spinner.setSelection(backgroundIndex) // set current background url
    }

    private fun getCurrentIndex() {
        if (sharedPreferences.contains(BACKGROUND_KEY)) {
            backgroundIndex = sharedPreferences.getInt(BACKGROUND_INDEX, 0) // get saved Index from SsharedPreferences
        }
    }

}