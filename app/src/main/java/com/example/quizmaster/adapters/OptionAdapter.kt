package com.example.quizmaster.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizmaster.R
import com.example.quizmaster.models.Questions
import kotlinx.android.synthetic.main.option_item.view.*

class OptionAdapter(val context : Context, val question : Questions) :
    RecyclerView.Adapter<OptionAdapter.OptionViewHolder>() {
    private var options : List<String> = listOf(question.option1,question.option2,question.option3,question.option4)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.option_item,parent,false)
        return OptionViewHolder(view)
    }

    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {
        holder.optionView.text = options[position]
        holder.itemView.setOnClickListener {
            question.userAnswer = options[position]
            notifyDataSetChanged()
        }

        if(question.userAnswer == options[position]){
            holder.itemView.quizOption.setBackgroundResource(R.drawable.option_item_selected_bg)
        }else{
            holder.itemView.quizOption.setBackgroundResource(R.drawable.option_item_bg)
        }

    }

    override fun getItemCount(): Int {
        return options.size
    }

    inner class OptionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val optionView = itemView.findViewById<TextView>(R.id.quizOption)
    }
}