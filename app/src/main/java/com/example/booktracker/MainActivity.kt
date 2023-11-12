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

    private val bookViewModel: BookViewModel by viewModels {
        ViewModelProvider.AndroidViewModelFactory.getInstance(application)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == addBookCode && resultCode == RESULT_OK) {
            data?.let {
                val book = it.getParcelableExtra<Book>("book")
                book?.let { bookViewModel.addBook(it) }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bookAdapter = BookAdapter()
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
    }
}
        /*
        val topAppBar: MaterialToolbar = findViewById(R.id.appBarLayout)
        // For example, to set a click listener on the MaterialToolbar:

        topAppBar.setOnMenuItemClickListener { menuItem ->
            // Handle toolbar item clicks here
            true
        }
         */


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BookTrackerTheme {
        Greeting("Android")
    }
}