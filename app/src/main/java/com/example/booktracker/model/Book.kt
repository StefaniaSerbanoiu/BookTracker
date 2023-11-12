package com.example.booktracker.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Book(
    var id: Int,
    var title: String = "",
    var year: Int = 0,
    var author: String = "",
    var genres: String = "",
    var rating: Float = 1.0f
) : Parcelable
{
    companion object{var index = 0}
    constructor(
        title: String,
        year: Int,
        author: String,
        genres: String,
        rating: Float
    ) : this(index, title, year, author, genres, rating)
    {
        index++;
    }

}
