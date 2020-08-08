package com.example.schedule.Adapters

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.schedule.R
import com.example.schedule.Models.viewpagerModel

class WalkAdapter(val list: List<viewpagerModel>, val context: Context) :PagerAdapter() {
    override fun isViewFromObject(view: View, `object`: Any): Boolean {

      return  view==`object`
    }

    override fun getCount(): Int {
       return list.size
    }


    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val inflater:LayoutInflater=context.getSystemService(LAYOUT_INFLATER_SERVICE)as LayoutInflater
        val view:View=inflater.inflate(R.layout.walk_layout,null)

        val pos=list[position]

        val imageView:ImageView=view.findViewById(R.id.walkImage)
        val text:TextView=view.findViewById(R.id.walkText)

        text.text=pos.description
        Glide.with(context).load(pos.image).into(imageView)


        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {

        container.removeView(`object` as View)


    }
}