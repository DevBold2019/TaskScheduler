package com.example.schedule

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.schedule.Models.menuModel
import com.example.schedule.Models.noteModel
import com.example.schedule.Models.tasksModel

@Dao
interface DaoClass {

    //Notes
    @Query("SELECT * FROM notes_table ORDER BY id DESC")
    fun getAllNotes():LiveData<List<noteModel>>
    @Insert
    fun addNotes(notes:noteModel)
    @Delete
    fun deleteNote(notes: noteModel)
    @Update
    fun upDateNote(notes: noteModel)

    //Tasks
    @Insert
    fun addTask(model:tasksModel)
    @Query("SELECT * FROM task_table ORDER BY sort ASC")
    fun getAllTasks():LiveData<List<tasksModel>>
    @Update
    fun updateTask(tasksModel: tasksModel)
    @Delete
    fun deleteTask(model: tasksModel)

    //Menus
    @Insert
    fun addMenu(model:menuModel)
    @Query("SELECT * FROM menus_table ORDER BY id DESC")
    fun getAllMenus():LiveData<List<menuModel>>






}