package com.example.schedule.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.schedule.Adapters.NotesAdapter
import com.example.schedule.Models.noteModel
import com.example.schedule.R
import com.example.schedule.viewModelClass

/**
 * A simple [Fragment] subclass.
 */
class NotesFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var list: List<noteModel>
    lateinit var adapter: NotesAdapter
    lateinit var layout: LinearLayout
    //lateinit var  viewModel: viewModelClass

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view:View=inflater.inflate(R.layout.fragment_notes, container, false)

        recyclerView=view.findViewById(R.id.notesRecycler)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager= LinearLayoutManager(context,RecyclerView.VERTICAL,false)

        layout=view.findViewById(R.id.emptyLayout)

     val viewModel=ViewModelProviders.of(this).get(viewModelClass::class.java)
        viewModel.listNotes().observe(this, object :Observer<List<noteModel>>  {

            override fun onChanged(t: List<noteModel>?) {

                if (t!!.isEmpty()){
                    layout.visibility=View.VISIBLE
                    recyclerView.visibility=View.GONE
                }

               adapter= NotesAdapter(t!!,container!!.context)
                adapter.notifyDataSetChanged()
                recyclerView.adapter=adapter

            }

        })

        return view
    }

    fun Toast(message:String){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}
