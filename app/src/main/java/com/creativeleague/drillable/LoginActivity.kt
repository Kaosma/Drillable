package com.creativeleague.drillable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    lateinit var textViewEmail : EditText
    lateinit var textViewPassword : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        textViewEmail = findViewById(R.id.textEmailLogin)
        textViewPassword = findViewById(R.id.textPasswordLogin)
        val createButton = findViewById<Button>(R.id.createAccountButton)
        val loginButton = findViewById<Button>(R.id.loginButton)

        loginButton.setOnClickListener {
            loginUser()
        }
        createButton.setOnClickListener {
            createUser()
        }
    }

    private fun loginUser() {
        if (textViewEmail.text.toString().isEmpty() || textViewPassword.text.toString().isEmpty())
            return
        auth.signInWithEmailAndPassword(textViewEmail.text.toString(), textViewPassword.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    println("!! LOGGED IN !!")
                    toMainActivity()
                } else {
                    println("!! NOT LOGGED IN!!")
                }
            }
    }

    private fun createUser() {
        if (textViewEmail.text.toString().isEmpty() || textViewPassword.text.toString().isEmpty())
            return
        auth.createUserWithEmailAndPassword(textViewEmail.text.toString(), textViewPassword.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    println("!! CREATED !!")
                } else {
                    println("!! NOT CREATED !!")
                }
            }
    }

    private fun toMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}

