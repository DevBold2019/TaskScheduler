package com.example.schedule

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProviders
import com.example.schedule.Fragments.NotesFragment
import com.example.schedule.Fragments.TasksFragment
import com.example.schedule.Models.menuModel
import com.example.schedule.Models.noteModel
import com.example.schedule.Models.tasksModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.*

class AddingActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    lateinit var linearLayout: LinearLayout
    lateinit var dialog: Dialog
    lateinit var card1:CardView
    lateinit var card2:CardView
    lateinit var textDate:TextView
    lateinit var title:EditText
    lateinit var description:EditText

    lateinit var noteTitle:EditText
    lateinit var noteDescription:EditText
    lateinit var button: Button
    lateinit var saveNote: Button

    var day=0
    var month=0
    var yearr=0


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adding)

        dialog= Dialog(this)
       dialog.setCanceledOnTouchOutside(false)
        dialog.setContentView(R.layout.date_picker_dialog)


        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)

        toolbar=findViewById(R.id.addToolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title=""

        title=findViewById(R.id.titleEditText)
        description=findViewById(R.id.descriptionEditText)

        noteTitle=findViewById(R.id.new_Note_Title)
        noteDescription=findViewById(R.id.new_note_description)


        button=findViewById(R.id.saveTaskButton)
        saveNote=findViewById(R.id.btnAddNewNote)

        card1=findViewById(R.id.newNoteCard)
        card2=findViewById(R.id.newTaskCard)

        textDate=findViewById(R.id.dueDateText)

        linearLayout=findViewById(R.id.dueDate)
        linearLayout.setOnClickListener {


            showDialog()



        }


        val intent:Intent=getIntent()
        if (intent.hasExtra("id")){

            supportActionBar!!.title="New Note"
            card1.visibility=View.VISIBLE
            card2.visibility=View.GONE

        }

        if (intent.hasExtra("task")){

            supportActionBar!!.title="New Task"
            card1.visibility=View.GONE
            card2.visibility=View.VISIBLE



        }

        button.setOnClickListener {

            if (description.text.trim().isNotEmpty() || title.text.trim().isNotEmpty()){

                val titleTask=title.text.toString()
                val descTask=description.text.toString()

                if(textDate.text.toString().trim().equals("")){

                    Toast("Please Pick a date")
                }else{

                    val taskdate=textDate.text.toString()
                    Toast(taskdate)

                    val getSort=Calendar.getInstance()
                    getSort.set(yearr,month,day)

                    val theSortNumber=getSort.timeInMillis

                    val  viewmodel=ViewModelProviders.of(this).get(viewModelClass::class.java)
                    viewmodel.addTasks(tasksModel(day,month,yearr,"Pending",titleTask,descTask,theSortNumber))

                    Toast("Task Saved")
                    dialog.dismiss()

                    val intent=Intent(applicationContext,TasksFragment::class.java)
                    setResult(1,intent)
                    finish()


                }


            }else{

                Toast("Can't save Blank Task")


            }

        }

        saveNote.setOnClickListener {

            if (noteDescription.text.trim().isNotEmpty() || noteTitle.text.trim().isNotEmpty()){

                val titleNote=noteTitle.text.toString()
                val descNote=noteDescription.text.toString()


                val  viewmodel=ViewModelProviders.of(this).get(viewModelClass::class.java)
                viewmodel.addNotes(noteModel(titleNote,descNote))

                Toast("Note Saved")
                dialog.dismiss()

                val intent=Intent(applicationContext,NotesFragment::class.java)
                setResult(2,intent)
                finish()
            }else{

                Toast("Can't save Blank Task")

            }

        }


    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    fun showDialog(){

        var years:String=""
        var months:Int=0
        var days:String=""

        var taskDueDates=""

        dialog.show()

        val picker:DatePicker=dialog.findViewById(R.id.pickADate)
        val btn:Button=dialog.findViewById(R.id.submitDate)


        val todayCalender:Calendar= Calendar.getInstance()
        val leo:Int = todayCalender.get(Calendar.DAY_OF_MONTH)
        val thisMonth:Int= todayCalender.get(Calendar.MONTH)
        val thisYear:Int= todayCalender.get(Calendar.YEAR)

        var addMonth:Int=0


        picker.minDate=Date().time

        //Toast("$addMonth is month")

        val today=Calendar.getInstance()


        picker.init(today[Calendar.YEAR],today[Calendar.MONTH],today[Calendar.DAY_OF_MONTH] ,DatePicker.OnDateChangedListener { view, year, monthOfYear, dayOfMonth ->

            years=year.toString()
            days=dayOfMonth.toString()
            months=monthOfYear

            addMonth=monthOfYear+1

            val cal: Calendar = Calendar.getInstance()
            cal.set(year,addMonth-1,dayOfMonth)

                    /*if (monthOfYear >= 0){
                        months = monthOfYear+
                    }*/

            val stdf = SimpleDateFormat("dd MMMM yyyy")

            taskDueDates= stdf.format(cal.time)
            Toast(taskDueDates)

        })

        btn.setOnClickListener {

            textDate.text=taskDueDates

            day= days.toInt()
            month=addMonth
            yearr=years.toInt()

            dialog.dismiss()

        }
    }

    fun Toast(message:String){
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }



}
