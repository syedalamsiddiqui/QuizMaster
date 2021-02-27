package com.example.quizmaster.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.quizmaster.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        firebaseAuth = FirebaseAuth.getInstance()
        btnLogintv.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        btnSignInUser.setOnClickListener {
            signUpUser()
        }
    }

    private fun signUpUser() {
        val email = etEmailAddressS.text.toString()
        val password = etPasswordS.text.toString()
        val cnfPassword = etCnfPasswordS.text.toString()
        if (password != cnfPassword) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            return
        }
        if (email.isBlank() || password.isBlank() || cnfPassword.isBlank()) {
            Toast.makeText(this, "Email and Password can not be empty", Toast.LENGTH_SHORT).show()
            return
        }
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Signed in successfully", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Error Signing Up", Toast.LENGTH_SHORT).show()
                }
            }
    }
}