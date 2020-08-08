package com.example.schedule.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Paint
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.schedule.Models.tasksModel
import com.example.schedule.R
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.*

class TasksAdapter(val list: List<tasksModel>,val context: Context) :RecyclerView.Adapter<TasksAdapter.viewholder>() {

    private var statusListener: OnItemClickListener? = null
    private var cardListener:OnCardClickListener?=null
    var onCardLongClick:onLongClickCardListener?=null;


    interface onLongClickCardListener{
        fun onCardLongClick(cardView: CardView,view: View,model: tasksModel,position: Int)

    }

    fun setOnLongCardClick(onLongClick:onLongClickCardListener){
        onCardLongClick=onLongClick
    }


    interface OnCardClickListener{

        fun onCardClick(cardView: CardView,view: View,model: tasksModel,position: Int)

    }

    fun setCardOnClickListener(CardListener:OnCardClickListener){

        cardListener=CardListener

    }

    interface  OnItemClickListener{

        fun OnItemClick(checkBox: CheckBox,view: CompoundButton,model: tasksModel,position: Int)

    }

    fun setOnclickListener(listener:OnItemClickListener){

        statusListener=listener


    }




    class viewholder(view: View) : RecyclerView.ViewHolder(view) {

        val date: TextView = view.findViewById(R.id.dateTextView)
        val title: TextView = view.findViewById(R.id.titleTextView)
        val desc: TextView = view.findViewById(R.id.descriptionTextView)
        val status: CheckBox =view.findViewById(R.id.statusCheckbox)
        val card:CardView=view.findViewById(R.id.taskCard)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {

        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.list_task_layout, parent, false)


        return viewholder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: viewholder, position: Int) {

        val pos = list[position]
        var date = pos.day
        var month = pos.month
        var year = pos.year


        holder.title.text = pos.title
        holder.desc.text = pos.description

        if (pos.status == "Complete"){

            holder.status.isChecked=true

        }else if (pos.status == "Incomplete"){

            holder.status.isChecked=false
        }
        val taskDueDate:Calendar= Calendar.getInstance()
        taskDueDate.set(year, month, date)


        val taskDueMonth=taskDueDate.get(Calendar.MONTH)
        val taskDueDay=taskDueDate.get(Calendar.DAY_OF_MONTH)
        val taskDueYear=taskDueDate.get(Calendar.YEAR)



        val todayCalender:Calendar= Calendar.getInstance()
        val today:Int = todayCalender.get(Calendar.DAY_OF_MONTH)
        val thisMonth:Int= todayCalender.get(Calendar.MONTH)
        val thisYear:Int= todayCalender.get(Calendar.YEAR)



        val dueDate= LocalDate.of(taskDueYear,taskDueMonth,taskDueDay)
        val toDate= LocalDate.of(thisYear,thisMonth+1,today)


        val differenceInDays= ChronoUnit.DAYS.between(toDate,dueDate)


        val dateTo = SimpleDateFormat("dd MMMM yyyy")

        val displayDate=Calendar.getInstance()
        displayDate.set(year,month-1,date)

        var date1=dateTo.format(displayDate.time)



        if (differenceInDays.toInt() == 0) {

            holder.date.text = "Today , $date1 , ${pos.status} Task"

        } else if (differenceInDays.toInt() == 1) {

            holder.date.text = "Tomorrow , $date1 , ${pos.status}"

        } else if (differenceInDays.toInt() < -1) {
            holder.date.text = "${pos.status} Task On , $date1 "
        } else if (differenceInDays.toInt() > 1) {
            holder.date.text = "$date1 , ${pos.status}"

        } else if (differenceInDays.toInt() == -1) {

                holder.date.text = "Yesterday , $date1 , ${pos.status} Task"

        } else {

            holder.date.text = "$date1 , ${pos.status}"
        }

        if(pos.status == "Complete"){

            holder.card.isClickable=false
            holder.status.isClickable=false
            holder.title.paintFlags = holder.title.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            holder.date.paintFlags = holder.date.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            holder.desc.paintFlags = holder.desc.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }

        holder.status.setOnCheckedChangeListener { buttonView, isChecked ->

            if (statusListener !=null && position!=RecyclerView.NO_POSITION){

                statusListener!!.OnItemClick(holder.status, buttonView!!,pos,position)
            }
        }
        holder.card.setOnClickListener { v ->
            if (cardListener !=null && position!=RecyclerView.NO_POSITION){

                cardListener!!.onCardClick(holder.card, v!!,pos,position)

            }
        }
        holder.card.setOnLongClickListener(object : View.OnLongClickListener {
            override fun onLongClick(v: View?): Boolean {

                if (onCardLongClick != null && position != RecyclerView.NO_POSITION) {
                    onCardLongClick!!.onCardLongClick(holder.card, v!!, pos, position)

                }
                return true;

            }

        })
    }

    fun toast(message: String) {
        android.widget.Toast.makeText(context, message, android.widget.Toast.LENGTH_SHORT).show()
    }
}