package com.example.quizmaster.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizmaster.R
import com.example.quizmaster.adapters.OptionAdapter
import com.example.quizmaster.models.Questions
import com.example.quizmaster.models.Quiz
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_question.*

class QuestionActivity : AppCompatActivity() {

    var quizzes: MutableList<Quiz>? = null
    var questions: MutableMap<String, Questions>? = null
    var index = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        setUpFireStore()
        setUpEventListener()
    }

    private fun setUpEventListener() {
        btnPrevious.setOnClickListener {
            index--
            bindViews()
        }
        btnNext.setOnClickListener {
            index++
            bindViews()
        }
        btnSubmit.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)
            val json = Gson().toJson(quizzes!![0])
            intent.putExtra("QUIZ", json)
            startActivity(intent)
        }
    }

    private fun setUpFireStore() {
        val firestore = FirebaseFirestore.getInstance()
        var date = intent.getStringExtra("DATE")
        if (date != null) {
            firestore.collection("quizzes").whereEqualTo("title", date)
                .get()
                .addOnSuccessListener {
                    if (it != null && !it.isEmpty) {
                        quizzes = it.toObjects(Quiz::class.java)
                        questions = quizzes!![0].questions
                        bindViews()
                    }
                }
        }

    }

    private fun bindViews() {
        btnNext.visibility = View.GONE
        btnSubmit.visibility = View.GONE
        btnPrevious.visibility = View.GONE

        if (index == 1) {
            btnNext.visibility = View.VISIBLE
        } else if (index == questions!!.size) {
            btnSubmit.visibility = View.VISIBLE
            btnPrevious.visibility = View.VISIBLE
        } else {
            btnNext.visibility = View.VISIBLE
            btnPrevious.visibility = View.VISIBLE
        }

        val question = questions!!["question$index"]
        question?.let {
            description.text = it.description
            val adapter = OptionAdapter(this, it)
            optionList.layoutManager = LinearLayoutManager(this)
            optionList.adapter = adapter
            optionList.setHasFixedSize(true)
        }
    }
}