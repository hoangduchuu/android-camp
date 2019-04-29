package com.khtn.androidcamp

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.register_fragment.*

/**
 * Created by Huu Hoang on 4/25/19.
 */
class RegisterFragment : BaseFragment() {
    interface Listener {
        fun openLoginScreen()
    }

    lateinit var mListener: Listener

    override fun getTagName(): String {
        return RegisterFragment::class.java.simpleName
    }

    override fun inflateView(): Int {
        return R.layout.register_fragment
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