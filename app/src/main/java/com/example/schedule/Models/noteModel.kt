package com.example.schedule.Models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
class noteModel(
    val title: String,
    val description: String,
    /*val category: String,*/

    @PrimaryKey(autoGenerate = true)
    val  id:Int=0

    ) {


}