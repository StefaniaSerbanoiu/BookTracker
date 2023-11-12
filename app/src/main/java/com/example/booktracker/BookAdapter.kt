package com.example.booktracker

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.compose.ui.res.booleanResource
import androidx.recyclerview.widget.RecyclerView
import com.example.booktracker.model.Book

class BookAdapter(private val bookViewModel: BookViewModel, private val context : Context) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {
    private var books: List<Book> = listOf()

    private var onItemClickListener: ((Book) -> Unit)? = null

    fun setOnItemClickListener(listener: (Book) -> Unit) {
        this.onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return BookViewHolder(view)
    }

    private fun showConfirmationDialog(book: Book) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Confirm Deletion")
            .setMessage("Are you sure you want to delete this book?")
            .setPositiveButton("Yes") { _, _ ->
                // yes -> delete
                bookViewModel.deleteBook(book.id)

                // Update the adapter data
                books = books.filter { it.id != book.id }
                notifyDataSetChanged()
            }
            .setNegativeButton("No") { _, _ ->
                // no -> cancel
            }
            .show()
    }

    override fun getItemCount(): Int = books.size

    fun setBooks(books: List<Book>) {
        this.books = books
        notifyDataSetChanged()
    }

    fun getBooks() : List<Book> {
        return books
    }

    fun update(newBook : Book, position: Int) {
        books[position].title = newBook.title
        books[position].year = newBook.year
        books[position].author = newBook.author
        books[position].genres = newBook.genres
        books[position].rating = newBook.rating
    }


    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val currentBook = books[position]
        holder.bind(currentBook)

        // Delete button click listener
        holder.itemView.findViewById<Button>(R.id.deleteButton).setOnClickListener {
            showConfirmationDialog(currentBook)
        }

        // Update button click listener
        holder.itemView.findViewById<Button>(R.id.updateButton).setOnClickListener {
            onItemClickListener?.invoke(currentBook)
        }
    }

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        private val title: TextView = itemView.findViewById(R.id.titleTextView)
        private val author: TextView = itemView.findViewById(R.id.authorTextView)
        private val year : TextView = itemView.findViewById(R.id.yearTextView)
        private val genres : TextView = itemView.findViewById(R.id.genresTextView)
        private val rating : TextView = itemView.findViewById(R.id.ratingTextView)
        @SuppressLint("SetTextI18n")
        fun bind(book: Book)
        {
            title.text = book.title
            author.text = "by " + book.author
            year.text = "published in " + book.year
            genres.text = "genres: " + book.genres
            rating.text = "rating: " + book.rating + "/5"
        }
    }
}
