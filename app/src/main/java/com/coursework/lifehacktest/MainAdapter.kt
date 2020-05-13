package com.coursework.lifehacktest

import android.content.Intent
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.coursework.lifehacktest.model.Company
import kotlinx.android.synthetic.main.company_card.view.*
import java.io.FileNotFoundException

class MainAdapter(val companiesList: List<Company>) : RecyclerView.Adapter<CustomViewHolder>() {

    // number of items
    override fun getItemCount(): Int {
        return companiesList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.company_card, parent, false)

        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        // set name for card
        holder?.view?.tv_name?.text = companiesList[position].name
        // load image to imageview
        try{
            Glide.with(holder.view.context)
                .asBitmap()
                .load("http://megakohz.bget.ru/test_task/" + companiesList[position].img)
                .into(holder?.view?.imageView)
        }
        catch (e: FileNotFoundException)
        {
            Log.d("error", e.message)
        }

        holder?.company = companiesList[position]
    }
}

class CustomViewHolder(val view : View, var company : Company? = null) : RecyclerView.ViewHolder(view){
    init {
        view.setOnClickListener {
            val intent = Intent(view.context, CompanyInfoActivity::class.java)
            intent.putExtra("id", company?.id)
            view.context.startActivity(intent)
        }
    }
}


