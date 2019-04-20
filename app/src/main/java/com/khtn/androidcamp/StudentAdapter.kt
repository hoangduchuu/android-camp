package com.khtn.androidcamp

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_student.view.*

/**
 * Created by Huu Hoang on 4/17/19.
 */
class StudentAdapter(var items: ArrayList<Student>, val context: Context) : RecyclerView.Adapter<StudentViewHolder>() {

    lateinit var mListener: StudentItemCLickListener

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): StudentViewHolder {
        return StudentViewHolder(LayoutInflater.from(context).inflate(R.layout.item_student, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(studentViewHolder: StudentViewHolder, position: Int) {
        studentViewHolder.tvName.text = "#$position ${items[position].name}"
        studentViewHolder.tvClass.text = items[position].classz
        Glide.with(context)
            .load( items[position].avatar)
            .centerCrop()
            .placeholder(R.drawable.student_place_holder)
            .into(studentViewHolder.ivAvatar)


        studentViewHolder.itemView.setOnClickListener {
            mListener.onItemCLicked(position)
        }

        studentViewHolder.itemView.setOnLongClickListener {
            mListener.onItemLongCLicked(position)
            true
        }

        studentViewHolder.tvInfo.setOnClickListener {
            mListener.onInfoItemClicked(position)
        }
    }

    fun setListener(listener: StudentItemCLickListener) {
        this.mListener = listener
    }

    fun setData(items: ArrayList<Student>){
        this.items = items
        notifyDataSetChanged()
    }

}

class StudentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var tvName = view.tvName
    var tvClass = view.tvClass
    var ivAvatar = view.ivAvatar
    var tvInfo = view.tvInfo
}
