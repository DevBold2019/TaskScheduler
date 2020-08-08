package com.example.schedule

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.schedule.Models.menuModel
import com.example.schedule.Models.noteModel
import com.example.schedule.Models.tasksModel
import com.example.testingapp.Databases.TaskDatabaseClass

class RepositoryClass(application: Application) {


     var databaseClass: TaskDatabaseClass = TaskDatabaseClass.getInstance(application.applicationContext)!!
    var allNotes:LiveData<List<noteModel>>
    var allTasks:LiveData<List<tasksModel>>
    var allMenus:LiveData<List<menuModel>>
    var daoClass: DaoClass

    init {

        daoClass=databaseClass.dao()
        allNotes=daoClass.getAllNotes()
        allTasks=daoClass.getAllTasks()
        allMenus=daoClass.getAllMenus()

    }

    fun insertNotes(noteModel: noteModel){

        InsertNotesSAsync(daoClass).execute(noteModel)

    }

    fun insertask(model: tasksModel){

        InsertTaskSAsync(daoClass).execute(model)

    }

    private class InsertNotesSAsync(daoClass: DaoClass):AsyncTask<noteModel,Unit,Unit>() {

        val dao=daoClass
        override fun doInBackground(vararg params: noteModel?) {

            dao.addNotes(params[0]!!)

        }

    }

    private class InsertTaskSAsync(daoClass: DaoClass):AsyncTask<tasksModel,Unit,Unit>() {

        val dao=daoClass
        override fun doInBackground(vararg params: tasksModel?) {

            dao.addTask(params[0]!!)

        }

    }

    fun listNotes():LiveData<List<noteModel>>{

        return allNotes
    }

    fun listTasks():LiveData<List<tasksModel>>{

        return allTasks
    }


    fun addMenu(model: menuModel){

        InsertMenuAsyncTask(daoClass).execute(model)
    }

    private class InsertMenuAsyncTask(daoClass: DaoClass):AsyncTask<menuModel,Unit,Unit>() {

        val dao=daoClass
        override fun doInBackground(vararg params: menuModel?) {

            dao.addMenu(params[0]!!)

        }

    }


    fun listMenus():LiveData<List<menuModel>>{

        return allMenus
    }

    fun updateTask(model: tasksModel){

        updateTaskAsync(daoClass).execute(model)
    }

    private class updateTaskAsync(dao: DaoClass) : AsyncTask<tasksModel,Unit,Unit>(){

        val updateDao=dao
        override fun doInBackground(vararg params: tasksModel?) {
            updateDao.updateTask(params[0]!!)

        }

    }
    fun deleteTask(model: tasksModel){
        deleteTaskAsync(daoClass).execute(model)
    }

    private class deleteTaskAsync(daoClass: DaoClass) : AsyncTask<tasksModel,Unit,Unit>(){
        val deleteDao=daoClass
        override fun doInBackground(vararg params: tasksModel?) {

            deleteDao.deleteTask(params[0]!!)

        }

    }


}