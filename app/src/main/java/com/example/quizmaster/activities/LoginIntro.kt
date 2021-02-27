package com.example.quizmaster.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quizmaster.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login_intro.*
import java.lang.Exception

class LoginIntro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_intro)
        val auth = FirebaseAuth.getInstance()
        if(auth.currentUser != null){
            redirect("MAIN")
        }
        btnGetStarted.setOnClickListener {
            redirect("LOGIN")
        }
    }

    private fun redirect(name : String){
        val intent = when(name){
                "LOGIN" -> Intent(this, LoginActivity::class.java)
                "MAIN" -> Intent(this, MainActivity::class.java)
            else -> throw Exception("no path exists")
            }
        startActivity(intent)
        finish()
    }
}