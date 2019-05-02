package com.khtn.androidcamp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by nampham on 2019-05-02.
 */
@Parcelize
data class Category(
    var categoryId: Int,
    var categoryName: String
) : Parcelable