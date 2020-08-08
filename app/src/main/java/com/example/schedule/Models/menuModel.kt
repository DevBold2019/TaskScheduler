package com.example.schedule.Models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "menus_table")
class menuModel(var title:String,var color:String,var count:String,@PrimaryKey(autoGenerate = true) var id:Int=0) {

}
