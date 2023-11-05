package com.example.booktracker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.booktracker.ui.theme.BookTrackerTheme
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : ComponentActivity() {
    private lateinit var bookAdapter: BookAdapter // Your RecyclerView adapter
    private lateinit var bookViewModel: BookViewModel // Your ViewModel

    // 2
    private val bookViewModel2: BookViewModel by lazy {
        ViewModelProvider(this).get(BookViewModel::class.java)
    }

    private lateinit var recyclerView: RecyclerView
    private val bookAdapter2 = BookAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //2
        //setContentView(R.layout.activity_main)

        //recyclerView = findViewById(R.id.recyclerView)
        //recyclerView.layoutManager = LinearLayoutManager(this)
        //recyclerView.adapter = bookAdapter


        /*
        bookViewModel.getBookListLiveData().observe(this, { bookList ->
            bookAdapter.submitList(bookList)
        })
        */


        /*
        val topAppBar: MaterialToolbar = findViewById(R.id.appBarLayout)
        // For example, to set a click listener on the MaterialToolbar:

        topAppBar.setOnMenuItemClickListener { menuItem ->
            // Handle toolbar item clicks here
            true
        }
         */
        setContentView(R.layout.activity_main)




        bookAdapter = BookAdapter()
        bookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = bookAdapter

        // Observe the LiveData and update the adapter
        bookViewModel.getAllBooks().observe(this) { books ->
            books?.let { bookAdapter.setBooks(it) }
        }

        val addBookButton: Button = findViewById(R.id.navigateToAddBookButton)

        addBookButton.setOnClickListener {
            val intent = Intent(this, AddBookActivity::class.java)
            startActivity(intent)
        }

    }
}

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