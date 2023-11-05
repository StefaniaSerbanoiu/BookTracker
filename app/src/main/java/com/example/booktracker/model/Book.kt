package com.example.booktracker.model

data class Book(
    val title: String,
    var year: Int,
    var author: String,
    var genres: List<String>,
    var rating: Float
)
