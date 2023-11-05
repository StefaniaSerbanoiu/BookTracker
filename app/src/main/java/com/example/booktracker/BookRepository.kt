package com.example.booktracker

import com.example.booktracker.model.Book

class BookRepository {
        private val books = mutableListOf<Book>()

        fun addBook(title: String, author: String, publicationYear: Int, genres: List<String>, rating : Float)
        {
            val book = Book(title,  publicationYear, author,  genres, rating)
            books.add(book)
        }

        fun getBookByTitle(title: String): Book?
        {
            return books.find { it.title == title }
        }

        fun updateBook(title: String, newAuthor: String, newYear: Int, genres: List<String>, rating: Float): Boolean
        {
            val bookToUpdate = books.find { it.title == title }
            return if (bookToUpdate != null)
            {
                bookToUpdate.author = newAuthor
                bookToUpdate.genres = genres
                bookToUpdate.rating = rating
                bookToUpdate.year = newYear
                true
            }
            else
            {
                false
            }
        }

        fun deleteBook(title: String): Boolean
        {
            val bookToRemove = books.find { it.title == title }
            return if (bookToRemove != null)
            {
                books.remove(bookToRemove)
                true
            }
            else
            {
                false
            }
        }
}


