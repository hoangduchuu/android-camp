package com.khtn.androidcamp

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.khtn.androidcamp.room.Student
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.item_student.view.*

/**
 * Created by Huu Hoang on 5/9/19.
 */

class StudentAdapter(
    val context: Context,
    var items: ArrayList<Student>
) : RecyclerView.Adapter<StudentAdapter.MyViewHolder>() {
    lateinit var myListener: MyListener

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_student, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvAge.text = items[position].age.toString()
        holder.tvName.text = items[position].name

        holder.btDelete.setOnClickListener { myListener.onDeleteButtonClicked(position) }
        holder.btEdit.setOnClickListener { myListener.onEditButtonClicked(position) }
    }

    fun setListener(listener: MyListener) {
        myListener = listener
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        this.items.removeAt(position)
        notifyItemRemoved(position)
    }

    fun setData(students: java.util.ArrayList<Student>) {
        this.items = students
        notifyDataSetChanged()
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvName = view.tvName
        var tvAge = view.tvAge
        var btDelete = view.btnDelete
        var btEdit = view.btnEdit
    }


}


