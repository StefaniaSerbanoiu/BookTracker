package com.example.booktracker

import com.example.booktracker.model.Book
import kotlin.random.Random

class BookRepository {
        private val books = mutableListOf<Book>()

        fun addBook(title: String, author: String, publicationYear: Int, genres: List<String>, rating : Float)
        {
            //val id = Random.nextInt(10, 10000)
            val book = Book(title,  publicationYear, author,  genres, rating)
            books.add(book)
        }

        fun getAllBooks() : List<Book> { return books; }

        fun getBookByTitle(title: String): Book? { return books.find { it.title == title } }

        fun getBookById(identifier: Int): Book? { return books.find { it.id == identifier } }

        fun updateBook(id: Int, newTitle: String, newAuthor: String, newYear: Int, genres: List<String>, rating: Float): Boolean
        {
            //val bookToUpdate = books.find { it.title == title }
            val bookToUpdate = this.getBookById(id)
            return if (bookToUpdate != null)
            {
                bookToUpdate.title = newTitle
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


