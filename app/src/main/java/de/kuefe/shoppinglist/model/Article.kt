package de.kuefe.shoppinglist.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Article(
    val id: Int,
    val quantity: Double,
    val unit: String,
    val description: String
): Parcelable
