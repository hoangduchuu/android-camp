package com.khtn.androidcamp.ROOM

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Huu Hoang on 4/17/19.
 */
@Parcelize
@Entity
data class Student(
    @PrimaryKey(autoGenerate = true) var id: Int? = null, var name: String, var classz: String, var avatarOfTeacher: Int
) : Parcelable {

    constructor() : this(null, "", "", -1)
}