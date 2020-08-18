package com.room.ps.bookkeeper

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
class Book(@PrimaryKey val id: String,
           val author: String,
           val title: String) {
}