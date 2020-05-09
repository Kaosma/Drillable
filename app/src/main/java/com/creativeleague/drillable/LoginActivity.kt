package com.creativeleague.drillable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    lateinit var textViewEmail : EditText
    lateinit var textViewPassword : EditText
    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        textViewEmail = findViewById(R.id.textEmailLogin)
        textViewPassword = findViewById(R.id.textPasswordLogin)
        auth = FirebaseAuth.getInstance()
        val createButton = findViewById<Button>(R.id.createAccountButton)

        createButton.setOnClickListener {
            createUser()
        }
    }

    fun createUser() {
        auth.createUserWithEmailAndPassword(textViewEmail.text.toString(), textViewPassword.text.toString())
    }
}
