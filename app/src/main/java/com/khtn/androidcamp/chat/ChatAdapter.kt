package com.khtn.androidcamp.chat

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.khtn.androidcamp.R


import java.util.ArrayList


/**
 * Created by nampham on 4/12/18.
 */
class ChatAdapter(private val mActivity: Activity) : RecyclerView.Adapter<ChatViewHolder>() {
    private val mData: MutableList<Message>?
    private val mLayoutInflater: LayoutInflater

    private val listener = object : ChatViewHolder.OnChatViewHolderListener {
        override fun onClick(position: Int) {

        }

        override fun onLongClick(position: Int) {

        }
    }


    init {
        mLayoutInflater = LayoutInflater.from(mActivity)
        mData = ArrayList()
    }

    fun setData(data: List<Message>) {
        mData!!.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    fun addData(data: Message) {
        mData!!.add(data)
        notifyItemInserted(mData.size - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = mLayoutInflater.inflate(R.layout.row_chat, parent, false)
        return ChatViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val entity = mData!![position]
        holder.renderUi(position, entity)
    }

    override fun getItemCount(): Int {
        return mData?.size ?: 0
    }

    companion object {
        private val TAG = ChatAdapter::class.java.simpleName
    }
}
