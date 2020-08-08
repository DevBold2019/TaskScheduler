package com.example.testingapp.Databases

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.schedule.DaoClass
import com.example.schedule.Models.menuModel
import com.example.schedule.Models.noteModel
import com.example.schedule.Models.tasksModel

@Database(entities = [noteModel::class,tasksModel::class,menuModel::class],version = 6)
abstract class TaskDatabaseClass: RoomDatabase() {

    abstract  fun dao():DaoClass

    companion object{

        var instance:TaskDatabaseClass?=null

        fun getInstance(context: Context):TaskDatabaseClass?{

            if (instance==null){

                synchronized(TaskDatabaseClass::class){

                    instance= Room.databaseBuilder(context.applicationContext,TaskDatabaseClass::class.java,"Task_Database").fallbackToDestructiveMigration().build()

                }

            }

            return instance

        }

        val callback= object : RoomDatabase.Callback() {

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                insertNoteAsyncTask(instance).execute()
                insertTaskAsyncTask(instance).execute()
                insertMenusTask(instance).execute()

            }

        }

        private class insertNoteAsyncTask(instance: TaskDatabaseClass?) : AsyncTask<Unit,Unit,Unit> (){

            var instance=instance!!.dao()

            override fun doInBackground(vararg params: Unit?) {

                instance.addNotes(noteModel("Code","Finish up project 1"))

            }
        }



        private class insertTaskAsyncTask(instance: TaskDatabaseClass?) : AsyncTask<Unit,Unit,Unit> (){

            var instance=instance!!.dao()

            override fun doInBackground(vararg params: Unit?) {



            }


        }




        private class insertMenusTask(instance: TaskDatabaseClass?) : AsyncTask<Unit,Unit,Unit> (){

            var instance=instance!!.dao()

            val orange="#FF4500"
            val greenLime="#00FF00"
            val crimson="#DC143C"
            val deepPink="#FF1493"
            val mediumOrchid="#4B0082"

            override fun doInBackground(vararg params: Unit?) {

                instance.addMenu(menuModel("Work",greenLime,"8 Tasks"))
                instance.addMenu(menuModel("Work",crimson,"8 Tasks"))





            }


        }

    }


}