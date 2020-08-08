package com.example.schedule.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.schedule.Models.noteModel
import com.example.schedule.Models.tasksModel
import com.example.schedule.R

class NotesAdapter(val list: List<noteModel>, val context: Context) : RecyclerView.Adapter<NotesAdapter.viewholder>() {

    class viewholder(view: View) : RecyclerView.ViewHolder(view) {

        val title: TextView =view.findViewById(R.id.notesTitleTextView)
        val desc: TextView =view.findViewById(R.id.notesDescriptionTextView)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {

        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.notes_layout,parent,false)


        return  viewholder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {

        val pos=list[position]

        holder.title.text=pos.title
        holder.desc.text=pos.description


    }


}