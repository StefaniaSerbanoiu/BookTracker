package com.example.booktracker

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.booktracker.model.Book
import com.example.booktracker.ui.theme.BookTrackerTheme
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : ComponentActivity() {
    private lateinit var bookAdapter: BookAdapter
    private val addBookCode = 1
    private val updateBookCode = 2

    private val bookViewModel: BookViewModel by viewModels {
        ViewModelProvider.AndroidViewModelFactory.getInstance(application)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("UpdateBookActivity", "onActivityResult: requestCode=$requestCode, resultCode=$resultCode")
        when (requestCode) {
            addBookCode -> {
                if (resultCode == RESULT_OK) {
                    data?.let {
                        val book = it.getParcelableExtra<Book>("book")
                        book?.let { bookViewModel.addBook(it) }
                    }
                }
            }

            updateBookCode -> {
                if (resultCode == RESULT_OK) {
                    data?.let {
                        val updatedBook = it.getParcelableExtra<Book>("updateBook")
                        updatedBook?.let { updated ->
                            val position = bookAdapter.getBooks().indexOfFirst { it.id == updated.id }
                            if (position != -1) {
                                bookAdapter.update(updatedBook, position)
                                bookAdapter.notifyItemChanged(position)
                            }
                        }
                    }
                }
            }


        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bookAdapter = BookAdapter(bookViewModel, this)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = bookAdapter

        bookViewModel.getAllBooks().observe(this) { books ->
            books?.let { bookAdapter.setBooks(it) }
        }

        val addBookButton: Button = findViewById(R.id.navigateToAddBookButton)

        addBookButton.setOnClickListener {
            val intent = Intent(this, AddBookActivity::class.java)
            startActivityForResult(intent, addBookCode)
        }

        bookAdapter.setOnItemClickListener { selectedBook ->
            // launch UpdateBookActivity
            val intent = Intent(this, UpdateBookActivity::class.java)
            intent.putExtra("updateBook", selectedBook)
            startActivityForResult(intent, updateBookCode)
        }
    }

    override fun onResume() {
        super.onResume()
    }

}
