package com.example.booktracker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.booktracker.model.Book


class UpdateBookActivity : ComponentActivity() {
    private val bookViewModel: BookViewModel by viewModels {
        ViewModelProvider.AndroidViewModelFactory.getInstance(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.update_book_screen)

        // getting the Book object from the intent
        val book: Book? = intent.getParcelableExtra("updateBook")

        if (book != null) {
            // put the book details in UI elements

            val titleEditText: EditText = findViewById(R.id.editTextTitle)
            val authorEditText: EditText = findViewById(R.id.editTextAuthor)
            val yearEditText: EditText = findViewById(R.id.editTextYear)
            val genresEditText: EditText = findViewById(R.id.editTextGenres)
            val ratingSpinner: EditText = findViewById(R.id.spinnerRating)

            titleEditText.setText(book.title)
            authorEditText.setText(book.author)
            yearEditText.setText(book.year.toString())
            genresEditText.setText(book.genres)
            ratingSpinner.setText(book.rating.toString()) // set the selection of the rating

            // cancel button click listener
            val cancelButton: Button = findViewById(R.id.cancelUpdateButton)
            cancelButton.setOnClickListener {
                finish()
            }

            // update button click listener
            val updateButton: Button = findViewById(R.id.updateButton)
            updateButton.setOnClickListener {
                // getting the new values
                val updatedTitle = titleEditText.text.toString()
                val updatedAuthor = authorEditText.text.toString()
                val updatedYear = yearEditText.text.toString().toIntOrNull() ?: 0
                val updatedGenres = genresEditText.text.toString()
                val updatedRatingText = ratingSpinner.text.toString()

                // check if the rating is a float between 1 and 10
                val updatedRating = updatedRatingText.toFloatOrNull()
                if (updatedRating == null || updatedRating !in 1.0f..10.0f) {
                    Toast.makeText(this, "Invalid rating!!! Rating should be between 1 and 10", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                // check if the year is <= 2023
                val validYear = updatedYear <= 2023
                if (!validYear) {
                    Toast.makeText(this, "Invalid year!!! Year should be less than or equal to 2023", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                // Create an updated Book object
                val updatedBook = Book(
                    id = book.id,
                    title = updatedTitle,
                    author = updatedAuthor,
                    year = updatedYear,
                    genres = updatedGenres,
                    rating = updatedRating
                )

                bookViewModel.updateBook(updatedBook)
                val resultIntent = Intent()
                resultIntent.putExtra("updateBook", updatedBook)
                setResult(RESULT_OK, resultIntent)

                finish()
            }
        } else {
            // if the Book object is null
            Toast.makeText(this, "Invalid book details", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}


