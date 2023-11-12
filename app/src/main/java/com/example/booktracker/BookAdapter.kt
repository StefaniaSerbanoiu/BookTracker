package com.example.booktracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.booktracker.model.Book

class BookAdapter : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {
    private var books: List<Book> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int)
    {
        val currentBook = books[position]
        holder.bind(currentBook)
    }

    override fun getItemCount(): Int = books.size

    fun setBooks(books: List<Book>)
    {
        this.books = books
        notifyDataSetChanged()
    }

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        private val title: TextView = itemView.findViewById(R.id.titleTextView)
        private val author: TextView = itemView.findViewById(R.id.authorTextView)

        fun bind(book: Book)
        {
            title.text = book.title
            author.text = book.author
        }
    }
}
