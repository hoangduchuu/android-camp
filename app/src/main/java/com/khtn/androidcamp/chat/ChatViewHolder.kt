package com.khtn.androidcamp.chat

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.khtn.androidcamp.R

import java.text.SimpleDateFormat
import java.util.Date


class ChatViewHolder(view: View, private val mListener: OnChatViewHolderListener?) : RecyclerView.ViewHolder(view),
    View.OnClickListener, View.OnLongClickListener {

    private var mposition = 0
    private val item: Message? = null

    private val tvTitle: TextView
    private val tvDescription: TextView

    interface OnChatViewHolderListener {
        fun onClick(position: Int)

        fun onLongClick(position: Int)
    }

    init {
        view.setOnClickListener(this)
        view.setOnLongClickListener(this)
        tvTitle = view.findViewById(R.id.tv_title)
        tvDescription = view.findViewById(R.id.tv_description)

    }

    fun renderUi(position: Int, entity: Message) {
        this.mposition = position
        tvTitle.text = entity.text

        val date = Date()
        date.time = entity.timestampe
        val sdf = SimpleDateFormat("HH:mm dd/MM/yyyy")
        val time = sdf.format(date)
        tvDescription.text = time
    }

    override fun onClick(v: View) {
        mListener?.onClick(mposition)
    }

    override fun onLongClick(v: View): Boolean {
        mListener?.onLongClick(mposition)
        return false
    }
}