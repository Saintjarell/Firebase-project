package com.example.firebaseauth1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    lateinit var myemail:EditText
    lateinit var mypasword:EditText
    lateinit var myconfpassword:EditText
    lateinit var signup:Button
    lateinit var mylogin:TextView
    private lateinit var auth:FirebaseAuth//creating firebase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myemail=findViewById(R.id.email)
        mypasword=findViewById(R.id.password)
        myconfpassword=findViewById(R.id.confirmpassword)
        signup=findViewById(R.id.signup)
        mylogin=findViewById(R.id.text)
        auth=Firebase.auth

        mylogin.setOnClickListener {
            var intent=Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        signup.setOnClickListener {
            SignUpUser()
        }
    }

    private fun SignUpUser(){
        var email=myemail.text.toString()
        var pwd=mypasword.text.toString()
        var confpwd=myconfpassword.text.toString()

        if (email.isBlank()||pwd.isBlank()||confpwd.isBlank()){
            Toast.makeText(this, "Email and Password can't be empty",Toast.LENGTH_LONG).show()
            return
        }
        else if (pwd!=confpwd){
            Toast.makeText(this, "Passwords do not match",Toast.LENGTH_LONG).show()
            return
        }

        auth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(this){
            if (it.isSuccessful){
                Toast.makeText(this, "Signed up successfully",Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this, "Failed to create",Toast.LENGTH_LONG).show()
            }
        }
    }
}