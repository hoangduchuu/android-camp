package com.khtn.androidcamp
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_student.view.*

/**
 * Created by Huu Hoang on 4/17/19.
 */
class StudentAdapter(val items: ArrayList<Student>, val context: Context) : RecyclerView.Adapter<StudentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): StudentViewHolder {
        return StudentViewHolder(LayoutInflater.from(context).inflate(R.layout.item_student, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(studentViewHolder: StudentViewHolder, position: Int) {
        studentViewHolder.tvName.text = items[position].name
        studentViewHolder.tvClass.text = items[position].classz
    }

}

class StudentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var tvName = view.tvName
    var tvClass = view.tvClass
}
