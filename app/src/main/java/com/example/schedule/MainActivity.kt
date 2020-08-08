package com.example.schedule

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.schedule.Fragments.MenuFragment
import com.example.schedule.Fragments.NotesFragment
import com.example.schedule.Fragments.ProfileFragment
import com.example.schedule.Fragments.TasksFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    lateinit var bottomNavigationView: BottomNavigationView
    lateinit var floatingActionButton: FloatingActionButton
    lateinit var toolbar: Toolbar
    lateinit var dialog: Dialog
     val NEW_TASK:Int=1
     val NEW_NOTE:Int=2
     val NEW_CHECKLIST:Int=3


    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)

        bottomNavigationView=findViewById(R.id.navbar)
        floatingActionButton=findViewById(R.id.fab)
        toolbar=findViewById(R.id.mainToolbar)


        setSupportActionBar(toolbar)


        dialog= Dialog(this)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setContentView(R.layout.dialog_layout)



        bottomNavigationView.setOnNavigationItemSelectedListener { p0 ->

            var selectedFragment:Fragment?
            when (p0.itemId){


                R.id.tasks ->{

                    supportActionBar!!.title="Tasks"
                    supportActionBar!!.subtitle="Tasks Lists"
                    floatingActionButton.visibility=View.VISIBLE
                    selectedFragment = TasksFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.contentLayout, selectedFragment).commit()

                }

                R.id.menus ->{

                    supportActionBar!!.title="Menu"
                    supportActionBar!!.subtitle="View/Add Menus"
                    floatingActionButton.visibility=View.GONE
                    selectedFragment = MenuFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.contentLayout, selectedFragment).commit()

                }

                R.id.notes ->{

                    supportActionBar!!.title="Notes"
                    supportActionBar!!.subtitle="Quick notes"
                    floatingActionButton.visibility=View.VISIBLE
                    selectedFragment = NotesFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.contentLayout, selectedFragment).commit()

                }

                R.id.profile ->{

                    supportActionBar!!.title="Profile"
                    supportActionBar!!.subtitle="Customize Profile"
                    floatingActionButton.visibility=View.GONE
                    selectedFragment = ProfileFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.contentLayout, selectedFragment).commit()

                }


            }



            true
        }

        if (savedInstanceState==null){

            supportFragmentManager.beginTransaction().replace(R.id.contentLayout, TasksFragment()).commit()

        }

        floatingActionButton.setOnClickListener {


            showDialog()

        }



    }


    fun showDialog(){

        dialog.show()

        val task:TextView=dialog.findViewById(R.id.newTask)
        val note:TextView=dialog.findViewById(R.id.newNote)
        task.setOnClickListener {

            val intent=Intent(this,AddingActivity::class.java)
            intent.putExtra("task","task")
            startActivityForResult(intent,NEW_TASK)
            dialog.dismiss()

        }

        note.setOnClickListener {

            val intent=Intent(this,AddingActivity::class.java)
            intent.putExtra("id","notes")
            startActivityForResult(intent,NEW_NOTE)
            dialog.dismiss()

        }


    }

    fun reUseToast(message:String){

        Toast.makeText(applicationContext,message,Toast.LENGTH_SHORT).show()

    }
}
