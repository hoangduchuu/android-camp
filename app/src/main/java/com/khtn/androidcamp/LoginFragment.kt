package com.khtn.androidcamp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.login_fragment.*

/**
 * Created by Huu Hoang on 4/25/19.
 */
class LoginFragment : BaseFragment() {

    interface Listener {
        fun openRegisterScreen()
        fun openForgotScreen()
    }

    lateinit var mListener: LoginFragment.Listener

    override fun getTagName(): String {
        return LoginFragment::class.java.simpleName
    }

    override fun inflateView(): Int {
        return R.layout.login_fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvRegister.setOnClickListener {
            mListener.openRegisterScreen()
        }

        tvReset.setOnClickListener {
            mListener.openForgotScreen()
        }
    }

    fun setListener(listener: LoginFragment.Listener) {
        mListener = listener
    }


}