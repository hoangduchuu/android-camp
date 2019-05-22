package com.khtn.androidcamp.login

import android.os.Bundle
import android.view.View
import com.khtn.androidcamp.BaseFragment
import com.khtn.androidcamp.R
import kotlinx.android.synthetic.main.forget_password_fragment.*

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