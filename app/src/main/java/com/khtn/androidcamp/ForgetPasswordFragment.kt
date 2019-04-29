package com.khtn.androidcamp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.forget_password_fragment.*
import kotlinx.android.synthetic.main.login_fragment.*

/**
 * Created by Huu Hoang on 4/25/19.
 */
class ForgetPasswordFragment : BaseFragment() {
    interface Listener {
        fun openLoginScreen()
    }

    lateinit var mListener: Listener

    override fun getTagName(): String {
        return ForgetPasswordFragment::class.java.simpleName
    }

    override fun inflateView(): Int {
        return R.layout.forget_password_fragment
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvLogin.setOnClickListener {
            mListener.openLoginScreen()
        }
    }

    fun setListener(listener: Listener) {
        mListener = listener
    }


}