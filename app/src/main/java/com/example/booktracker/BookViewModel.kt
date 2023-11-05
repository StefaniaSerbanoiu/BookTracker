package com.example.booktracker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.booktracker.model.Book


class BookViewModel : ViewModel() {
    private val books = mutableListOf<Book>()
    private val booksLiveData = MutableLiveData<List<Book>>()

    init {
        // Initialize with some sample data
        books.addAll(listOf(
            Book("Book 1", 2000, "Author 1", listOf("Genre1", "Genre2"), 4.5f),
            Book("Book 2", 2005, "Author 2", listOf("Genre3"), 4.0f)
            // Add more sample books if needed
        ))
        booksLiveData.value = books
    }

    fun getAllBooks(): LiveData<List<Book>> {
        return booksLiveData
    }

    fun addBook(book: Book) {
        books.add(book)
        booksLiveData.value = books
    }

    fun updateBook(updatedBook: Book) {
        val index = books.indexOfFirst { it.title == updatedBook.title }
        if (index != -1) {
            books[index] = updatedBook
            booksLiveData.value = books
        }
    }

    fun deleteBook(title: String) {
        books.removeIf { it.title == title }
        booksLiveData.value = books
    }

    private fun generateSampleBooks(): List<Book> {
        // You can replace this with your own data retrieval logic
        val books = mutableListOf<Book>()
        books.add(Book("Book 1", 2022, "Author 1", listOf("Genre 1", "Genre 2"), 4.5f))
        books.add(Book("Book 2", 2021, "Author 2", listOf("Genre 2", "Genre 3"), 4.0f))
        // Add more books as needed
        return books
    }
}
