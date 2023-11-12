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
            Book(1, "Kingkiller chronicles", 2007, "Patrick Rothfuss", "fantasy, adventure", 10f),
            Book(2, "The blade itself", 2006, "Joe Abercrombie", "grimdark fantasy", 4.0f),
            Book(3, "Circe", 2018, "Madeline Miller", "mythology, retelling", 4.5f),
            Book(4, "Salem's Lot", 1975, "Stephen King", "horror", 4.0f)
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
