package com.khtn.androidcamp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.register_fragment.*

/**
 * Created by Huu Hoang on 4/25/19.
 */
class RegisterFragment : Fragment() {
    interface Listener {
        fun openLoginScreen()
    }

    lateinit var mListener: RegisterFragment.Listener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.register_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvLogin.setOnClickListener {
            mListener.openLoginScreen()
        }
    }

    fun setListener(listener: RegisterFragment.Listener) {
        mListener = listener
    }


}