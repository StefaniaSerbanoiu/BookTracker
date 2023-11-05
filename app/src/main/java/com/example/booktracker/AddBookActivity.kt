package com.example.booktracker

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.booktracker.model.Book

class AddBookActivity : ComponentActivity() {
    private val bookViewModel: BookViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_book_screen)
        val editTextTitle = findViewById<EditText>(R.id.editTextTitle)
        val editTextAuthor = findViewById<EditText>(R.id.editTextAuthor)
        val editTextYear = findViewById<EditText>(R.id.editTextYear)
        val editTextGenres = findViewById<EditText>(R.id.editTextGenres)
        val spinnerRating = findViewById<Spinner>(R.id.spinnerRating)
        val addButton = findViewById<Button>(R.id.addButton)

        addButton.setOnClickListener {
            addBook(
                editTextTitle.text.toString(),
                editTextAuthor.text.toString(),
                editTextYear.text.toString().toIntOrNull() ?: 0,
                editTextGenres.text.split(",").map { it.trim() },
                spinnerRating.selectedItem.toString().toFloatOrNull() ?: 0.0f
            )
        }
    }

    private fun addBook(title: String, author: String, year: Int, genres: List<String>, rating: Float) {
        val book = Book(title, year, author, genres, rating)


        // Add book to the repository or ViewModel here
        // Example: bookRepository.addBook(book)
        bookViewModel.addBook(book)

        finish() // Finish the activity after adding the book
    }
}
