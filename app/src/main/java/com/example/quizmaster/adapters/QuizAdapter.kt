package com.example.quizmaster.adapters

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizmaster.R
import com.example.quizmaster.activities.QuestionActivity
import com.example.quizmaster.models.Quiz
import com.example.quizmaster.utils.ColorPicker
import com.example.quizmaster.utils.IconPicker

class QuizAdapter(val context: Context, val quizzes: List<Quiz>) :
    RecyclerView.Adapter<QuizAdapter.QuizViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.quiz_item, parent, false)
        return QuizViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        holder.textViewTitle.text = quizzes[position].title
        holder.cardContainer.setCardBackgroundColor(Color.parseColor(ColorPicker.getColor()))
        holder.iconView.setImageResource(IconPicker.getIcon())
        holder.itemView.setOnClickListener {
            val intent = Intent(context,QuestionActivity::class.java)
            intent.putExtra("DATE", quizzes[position].title)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return quizzes.size
    }

    inner class QuizViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewTitle = itemView.findViewById<TextView>(R.id.quizTitle)
        var iconView = itemView.findViewById<ImageView>(R.id.quizIcon)
        var cardContainer = itemView.findViewById<CardView>(R.id.cardContainer)
    }
}