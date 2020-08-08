package com.example.schedule

import android.app.Application
import androidx.lifecycle.*
import com.example.schedule.Models.menuModel
import com.example.schedule.Models.noteModel
import com.example.schedule.Models.tasksModel

public class viewModelClass(application: Application) : AndroidViewModel(application) {

     val repository:RepositoryClass=RepositoryClass(application)
     val allNotes:LiveData<List<noteModel>> = repository.allNotes
     val allTasks:LiveData<List<tasksModel>> = repository.allTasks
     val allMenus:LiveData<List<menuModel>> = repository.allMenus



    fun addNotes(model: noteModel){
        repository.insertNotes(model)
    }

    fun listNotes():LiveData<List<noteModel>>{

        return allNotes
    }

    fun addTasks(model: tasksModel){
        repository.insertask(model)
    }

    fun listTasks():LiveData<List<tasksModel>>{

        return allTasks
    }

    fun addMenu(model: menuModel){

        repository.addMenu(model)
    }

    fun listMenu():LiveData<List<menuModel>>{

        return allMenus
    }
    fun updateTasks(model: tasksModel){

        repository.updateTask(model)
    }
    fun deleteTask(model: tasksModel){

        repository.deleteTask(model)

    }





}