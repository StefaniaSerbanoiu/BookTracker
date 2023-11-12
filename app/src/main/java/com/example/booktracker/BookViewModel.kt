package com.example.booktracker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.booktracker.model.Book


class BookViewModel : ViewModel() {
    private val books = mutableListOf<Book>()
    private val booksLiveData = MutableLiveData<List<Book>>()

    init {
        books.addAll(listOf(
            Book(1, "Book 1", 2000, "Author 1", "Genre1,  Genre2", 4.5f),
            Book(2, "Book 2", 2005, "Author 2", "Genre3", 4.0f),
            Book(3, "Book 3", 2022, "Author 1", "Genre 1, Genre 2", 4.5f),
            Book(4, "Book 4", 2021, "Author 2", "Genre 2", 4.0f)
        ))
        booksLiveData.value = books
    }

    fun getAllBooks(): LiveData<List<Book>> { return booksLiveData }

    fun getSize() : Int = books.size

    fun addBook(book: Book)
    {
        books.add(book)
        booksLiveData.value = books.toList()
    }

    fun updateBook(updatedBook: Book)
    {
        val index = books.indexOfFirst { it.title == updatedBook.title }
        if (index != -1)
        {
            books[index] = updatedBook
            booksLiveData.value = books
        }
    }

    fun deleteBook(id: Int)
    {
        books.removeIf { it.id == id }
        booksLiveData.value = books
    }
}
