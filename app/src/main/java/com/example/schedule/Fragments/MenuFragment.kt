package com.example.schedule.Fragments

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.schedule.Adapters.MenuFragmentAdapter
import com.example.schedule.Models.menuModel

import com.example.schedule.R
import com.example.schedule.viewModelClass
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MenuFragment : Fragment() {

    lateinit var  recyclerView: RecyclerView
    lateinit var imageButton: FloatingActionButton
    lateinit var adapter: MenuFragmentAdapter
    lateinit var dialog: Dialog
    lateinit var list: MutableList<menuModel>
    lateinit var layout: LinearLayout

    val orange="#FF4500"
    val greenLime="#00FF00"
    val crimson="#DC143C"
    val deepPink="#FF1493"
    val mediumOrchid="#4B0082"

    lateinit var viewmodel:viewModelClass

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view:View=inflater.inflate(R.layout.fragment_menu_, container, false)
        dialog= Dialog(container!!.context)
        dialog.setContentView(R.layout.new_menu_layout)
        dialog.setCanceledOnTouchOutside(true)

        recyclerView=view.findViewById(R.id.menuRecycler)
        imageButton=view.findViewById(R.id.addMenuFab)
        layout=view.findViewById(R.id.emptyLayout)

        recyclerView.layoutManager=GridLayoutManager(container!!.context,2)
        recyclerView.setHasFixedSize(false)


        imageButton.setOnClickListener {

            addMenu()
        }


        viewmodel=ViewModelProviders.of(this).get(viewModelClass::class.java)
        viewmodel.allMenus.observe(this, object : Observer<List<menuModel>> {

            override fun onChanged(t: List<menuModel>?) {

                if (t!!.isEmpty()){

                    layout.visibility=View.VISIBLE
                    recyclerView.visibility=View.GONE

                    return
                }
                layout.visibility=View.GONE
                recyclerView.visibility=View.VISIBLE

                adapter= MenuFragmentAdapter(t,container.context)
                adapter.notifyDataSetChanged()
                recyclerView.adapter=adapter


            }


        })







        return view
    }

    fun addMenu(){

    /* list.add( menuModel("School",R.color.colorAccent,"5 Tasks"))
        adapter.notifyDataSetChanged()
        Toast("Added")*/

        showDialog()

    }

    fun Toast(message:String){
        android.widget.Toast.makeText(context, message, android.widget.Toast.LENGTH_SHORT).show()
    }

    fun showDialog(){

        var color:String=""
        /*val orange="#FF4500"
        val greenLime="#00FF00"
        val crimson="#DC143C"
        val deepPink="#FF1493"
        val mediumOrchid="#4B0082"
*/

        dialog.show()
        val title:EditText=dialog.findViewById(R.id.new_Menu_Title)
        val btn:Button=dialog.findViewById(R.id.btnAddMenu)

        val card1:CardView=dialog.findViewById(R.id.card1)
        val card2:CardView=dialog.findViewById(R.id.card2)
        val card3:CardView=dialog.findViewById(R.id.card3)
        val card4:CardView=dialog.findViewById(R.id.card4)
        val card5:CardView=dialog.findViewById(R.id.card5)

        card1.setOnClickListener { color=orange}
        card2.setOnClickListener { color=greenLime}
        card3.setOnClickListener { color=crimson}
        card4.setOnClickListener { color=deepPink}
        card5.setOnClickListener {  color=mediumOrchid}


        btn.setOnClickListener {

            if (title.text.isEmpty()){
                title.error="Empty Title"

            }else{

                if (color.equals("")){

                    color="#6200EE"
                }

                val titleText=title.text.toString()

                viewmodel.addMenu(menuModel(titleText,color,"0 Tasks"))
                Toast("Saved")

                title.text.clear()
                dialog.dismiss()

            }


        }





    }

}
