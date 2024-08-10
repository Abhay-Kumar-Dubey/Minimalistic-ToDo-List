package com.firefuro.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Sign_In_Screen : AppCompatActivity() {
    lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in_screen)
        var enteredEmail = findViewById<EditText>(R.id.signinEmail)
        var enteredPassword = findViewById<EditText>(R.id.signinPassword)
        var SignInButton = findViewById<Button>(R.id.SignInButton)

        SignInButton.setOnClickListener {
            var EmailVariable = enteredEmail.text.toString()
            var PasswordVariable = enteredPassword.text.toString()

            if (EmailVariable.isNotEmpty() || PasswordVariable.isNotEmpty()){
                readDatabase(EmailVariable,PasswordVariable)
            }else{
                Toast.makeText(this,"Failed",Toast.LENGTH_LONG).show()
            }

        }
    }

    fun readDatabase(email:String , password:String){
        databaseReference= FirebaseDatabase.getInstance().getReference("Users")
        databaseReference.child(email).get().addOnSuccessListener {
            if (it.exists()){
                databaseReference.child(email).child("passwordData").get().addOnSuccessListener {
                    if (it.exists()){
                       val intent=Intent(this,HomePage::class.java)
                        startActivity(intent)
                        Toast.makeText(this,"Successfully Logged In",Toast.LENGTH_SHORT).show()

                    }else{
                        Toast.makeText(this,"Password or Email Id is Wrong",Toast.LENGTH_LONG).show()
                    }
            }

            }else{Toast.makeText(this,"Email ID does not Exist in Database",Toast.LENGTH_LONG).show()}

        }.addOnFailureListener{

        }
    }
}