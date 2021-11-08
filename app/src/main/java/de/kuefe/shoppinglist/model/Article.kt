package de.kuefe.shoppinglist.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Article(
    val id: Int,
    var quantity: Double,
    var unit: String,
    var description: String
): Parcelable
