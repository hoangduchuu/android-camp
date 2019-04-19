package com.khtn.androidcamp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Huu Hoang on 4/17/19.
 */
@Parcelize
data class Student(val name: String, val classz: String, val avatar: String): Parcelable