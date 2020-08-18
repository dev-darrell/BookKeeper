package com.room.ps.bookkeeper

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.book_list_item.view.*

class BookRecyclerAdapter(private val context: Context,
            private val deleteClickListener: OnDeleteClickListener): RecyclerView.Adapter<BookRecyclerAdapter.ViewHolder>() {

    private var bookList: List<Book> = mutableListOf()
    interface OnDeleteClickListener{
        fun onDeleteClickListener(myBook: Book)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.book_list_item,
        parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return bookList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = bookList[position]
        holder.setData(book.author, book.title, position)
        holder.setClickListeners()
    }

    fun setBooks(books: List<Book>) {
        bookList = books
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var pos: Int = 0
        fun setData(author: String, title: String, position: Int) {
            itemView.tv_author.text = author
            itemView.tv_book.text = title
            this.pos = position
        }

        fun setClickListeners() {
            itemView.setOnClickListener {
                val intent = Intent(context, NewBookActivity::class.java)
                intent.putExtra("id", bookList[pos].id)
                intent.putExtra("author", bookList[pos].author)
                intent.putExtra("title", bookList[pos].title)
                (context as Activity).startActivityForResult(intent, MainActivity.UPDATE_BOOK_ACTIVITY_REQUEST_CODE)
            }

            itemView.ic_delete_book.setOnClickListener {
                deleteClickListener.onDeleteClickListener(bookList[pos])
            }
        }
    }
}