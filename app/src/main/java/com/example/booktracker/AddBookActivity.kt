package com.example.booktracker

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.booktracker.model.Book

class AddBookActivity : ComponentActivity() {
    private val bookViewModel: BookViewModel by viewModels {
        ViewModelProvider.AndroidViewModelFactory.getInstance(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_book_screen)

        val editTextTitle = findViewById<EditText>(R.id.editTextTitle)
        val editTextAuthor = findViewById<EditText>(R.id.editTextAuthor)
        val editTextYear = findViewById<EditText>(R.id.editTextYear)
        val editTextGenres = findViewById<EditText>(R.id.editTextGenres)
        val spinnerRating = findViewById<EditText>(R.id.spinnerRating)

        val addButton = findViewById<Button>(R.id.addButton)
        val cancelAddButton = findViewById<Button>(R.id.cancelAddButton)

        cancelAddButton.setOnClickListener {
            finish()
        }

        addButton.setOnClickListener {
            addBook(
                editTextTitle.text.toString(),
                editTextAuthor.text.toString(),
                editTextYear.text.toString(),
                editTextGenres.text.toString(),
                spinnerRating.text.toString()
            )
        }
    }

    private fun addBook(title: String, author: String, year: String, genres: String, rating: String) {
        // check if the rating is a float between 1 and 10
        val floatRating = rating.toFloatOrNull()
        if (floatRating == null || floatRating !in 1.0f..10.0f) {
            showToast("Invalid rating!!! Rating should be between 1 and 10")
            return
        }

        // check if the year is <= 2023
        val intYear = year.toIntOrNull()
        if (intYear == null || intYear <= 0 || intYear > 2023) {
            showToast("Invalid year!!! Year should be less than or equal to 2023")
            return
        }

        // creating a valid book
        val book = Book(
            title = title,
            author = author,
            year = intYear,
            genres = genres,
            rating = floatRating
        )

        // passing the book to the main (who called this activity)
        val resultIntent = Intent()
        resultIntent.putExtra("book", book)
        setResult(RESULT_OK, resultIntent)
        println(bookViewModel.getSize())
        finish()
    }

    private fun showToast(message: String) {
        // Display a toast message
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
