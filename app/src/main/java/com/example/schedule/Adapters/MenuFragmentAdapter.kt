package com.example.schedule.Adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.schedule.Models.menuModel
import com.example.schedule.R

class MenuFragmentAdapter (val list: List<menuModel>, val context: Context) : RecyclerView.Adapter<MenuFragmentAdapter.viewholder>() {

    class viewholder(view: View) :RecyclerView.ViewHolder(view) {

        val count :TextView =view.findViewById(R.id.menuCount)
        val title: TextView =view.findViewById(R.id.menuTitle)
        val card: CardView =view.findViewById(R.id.colorCard)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {

        val view:View=LayoutInflater.from(parent.context).inflate(R.layout.menu_layout,parent,false)



        return viewholder(view)

    }

    override fun getItemCount(): Int {
    return list.size
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {

        val pos=list[position]

        holder.title.text=pos.title
        holder.count.text=pos.count
        holder.card.setCardBackgroundColor(Color.parseColor(pos.color))

    }


}
