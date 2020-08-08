package com.example.schedule.Fragments


import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.schedule.Adapters.TasksAdapter
import com.example.schedule.Models.tasksModel
import com.example.schedule.R
import com.example.schedule.viewModelClass
import com.google.android.material.snackbar.Snackbar
import java.util.*


class TasksFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter:TasksAdapter
    lateinit var layout: LinearLayout
    lateinit var viewModel:viewModelClass
    lateinit var dialog:Dialog
    lateinit var snackbar: Snackbar


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

       val view:View= inflater.inflate(R.layout.fragment_tasks, container, false)


        dialog= Dialog(container!!.context)
        dialog.setContentView(R.layout.update_status_layout)
        dialog.setCanceledOnTouchOutside(true)





        recyclerView=view.findViewById(R.id.taskRecycler)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager=LinearLayoutManager(context,RecyclerView.VERTICAL,false)

        layout=view.findViewById(R.id.emptyLayout)

        viewModel= ViewModelProviders.of(this).get(viewModelClass::class.java)

        viewModel.allTasks.observe(this, object : Observer<List<tasksModel>> {

            override fun onChanged(t: List<tasksModel>?) {

                if (t!!.isEmpty()){

                    layout.visibility=View.VISIBLE
                    recyclerView.visibility=View.GONE

                    return
                }

                layout.visibility=View.GONE
                recyclerView.visibility=View.VISIBLE


                adapter= TasksAdapter(t,container!!.context)
                adapter.setOnclickListener(object : TasksAdapter.OnItemClickListener {
                    override fun OnItemClick(checkBox: CheckBox, view: CompoundButton, model: tasksModel, position: Int) {

                        val updateTitle=model.title
                        val updateDescription=model.description
                        val updateDay=model.day
                        val updateMonth=model.month
                        val updateYear=model.year
                        val id=model.id
                        val sort=model.sort
                        var status=model.status
                        var checked=checkBox.isChecked

                        updateTaskStatus(updateTitle,updateDescription,updateDay,updateMonth,updateYear,id,status,checked,sort)



                    }

                })


                adapter.setCardOnClickListener(object : TasksAdapter.OnCardClickListener {

                    override fun onCardClick(cardView: CardView, view: View, model: tasksModel, position: Int) {




                    }


                })

                adapter.setOnLongCardClick(object : TasksAdapter.onLongClickCardListener {
                    override fun onCardLongClick(cardView: CardView, view: View, model: tasksModel, position: Int) {


                        val id=model.id
                        val title=model.title
                        val description=model.description
                        val day=model.day
                        val month=model.month
                        val year=model.year
                        val status=model.status
                        val sort=model.sort

                       //toast("Id Is: $id\nTitle Is: $title\nDesc Is: $description\nDay Is:$day\nMonth Is:$month\nYear Is:$year\nStatus Is:$status\n LongSort :$sort")

                        deleteTask(id,title,description,day,month,year,status,sort)


                    }
                })
                adapter.notifyDataSetChanged()
                recyclerView.adapter=adapter
            }
        })
        return view
    }

    @SuppressLint("SetTextI18n")
    private fun deleteTask(id: Int, title: String, description: String, day: Int, month: Int, year: Int, status: String, sort: Long) {

        dialog.show()
        val content:TextView=dialog.findViewById(R.id.contentTextView)
        val yes:TextView=dialog.findViewById(R.id.yesButton)
        val no:TextView=dialog.findViewById(R.id.noButton)
        content.text="Delete Task Id : $id ?"

        yes.setOnClickListener {

            val deleteModel=tasksModel(day, month, year, status, title, description,sort,id)
            viewModel.deleteTask(deleteModel)

            val snack:Snackbar= Snackbar.make(it,"Deleted SuccessFully ",Snackbar.LENGTH_LONG)
            snack.show()


            dialog.dismiss()


        }

        no.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                dialog.dismiss()
            }


        })
        adapter.notifyDataSetChanged()

    }

    private fun updateTaskStatus(updateTitle: String, updateDescription: String, updateDay: Int, updateMonth: Int, updateYear: Int,id: Int, status: String, checked: Boolean, sort: Long) {
        dialog.show()

        val yes:TextView=dialog.findViewById(R.id.yesButton)
        val no:TextView=dialog.findViewById(R.id.noButton)
        var updatedStatus=""

        yes.setOnClickListener {
            if (checked){
                updatedStatus="Complete"
            }else{
                updatedStatus="Incomplete"
            }
            val updateModel=tasksModel(updateDay, updateMonth, updateYear,updatedStatus, updateTitle, updateDescription,sort,id)
            viewModel.updateTasks(updateModel)
            //toast("Task Status updates To :\t $status ")
            dialog.dismiss()

        }
        no.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                dialog.dismiss()
            }


        })

    }

    fun toast(message:String){
        Toast.makeText(context, message,Toast.LENGTH_LONG).show()
    }

}
