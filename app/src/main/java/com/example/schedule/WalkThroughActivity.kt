package com.example.schedule

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.viewpager.widget.ViewPager
import com.example.schedule.Adapters.WalkAdapter
import com.example.schedule.Models.viewpagerModel
import com.google.android.material.tabs.TabLayout

class WalkThroughActivity : AppCompatActivity() {

    lateinit var viewPager: ViewPager
    lateinit var tabLayout: TabLayout
    lateinit var button:Button
    lateinit var adapter: WalkAdapter
    lateinit var list: List<viewpagerModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_walk_through)

        viewPager=findViewById(R.id.viewPager)
        tabLayout=findViewById(R.id.tabLayout)
        button=findViewById(R.id.startedBtn)


        list= listOf(
            viewpagerModel(
                R.drawable.image1,
                "Image 1 with Description"
            ),
            viewpagerModel(
                R.drawable.pic2,
                "Image 2 with Description"
            ),
            viewpagerModel(
                R.drawable.pic4,
                "Image 3 with Description"
            ),
            viewpagerModel(
                R.drawable.pic5,
                "Image 4 with Description"
            )
        )

        adapter= WalkAdapter(list, applicationContext)
        viewPager.adapter=adapter
        tabLayout.setupWithViewPager(viewPager)

      if (viewPager.currentItem == -1){

          toast("Last One")
      }



        button.setOnClickListener {

            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()


        }




    }

    fun toast(message: String) {
        android.widget.Toast.makeText(applicationContext, message, android.widget.Toast.LENGTH_SHORT).show()
    }


}
