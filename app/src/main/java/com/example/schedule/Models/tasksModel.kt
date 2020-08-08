package com.example.schedule.Models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "task_table")
    class tasksModel(var day:Int,var month:Int,var year:Int,var status:String,var title:String,var description:String,var sort:Long,
                 @PrimaryKey(autoGenerate = true)
                 var id:Int=0

) {

}
