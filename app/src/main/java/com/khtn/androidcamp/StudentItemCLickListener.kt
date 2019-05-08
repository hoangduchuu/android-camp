package com.khtn.androidcamp

/**
 * Created by Huu Hoang on 4/17/19.
 */

interface StudentItemCLickListener {
    fun onItemCLicked(position: Int)
    fun onItemLongCLicked(position: Int)
    fun onEditIconClicked(position: Int)
}