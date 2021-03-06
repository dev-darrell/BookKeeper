package com.room.ps.bookkeeper

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import kotlinx.android.synthetic.main.activity_new.*
import kotlinx.android.synthetic.main.book_list_item.*

class NewBookActivity : AppCompatActivity() {
    var id: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)


        val bundle: Bundle? = intent.extras
        bundle?.let {
            id = bundle.getString("id")
            val bookTitle = bundle.getString("title")
            val author = bundle.getString("author")


            etAuthorName.setText(author)
            etBookName.setText(bookTitle)
        }


        bSave.setOnClickListener {
            val resultIntent = Intent()
            val updatedAuthor = etAuthorName.text.toString()
            val updatedBook = etBookName.text.toString()

            resultIntent.putExtra(ID, id)
            resultIntent.putExtra(NEW_AUTHOR, updatedAuthor)
            resultIntent.putExtra(NEW_BOOK, updatedBook)
            setResult(Activity.RESULT_OK, resultIntent)

            finish()
        }

        bCancel.setOnClickListener {
            finish()
        }
    }

    companion object {
        const val ID = "book_id"
        const val NEW_AUTHOR = "new author"
        const val NEW_BOOK = "new book"
    }
}