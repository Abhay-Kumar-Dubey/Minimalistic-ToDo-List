package com.firefuro.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val UserName=findViewById<EditText>(R.id.UserName)
        val UserEmail=findViewById<EditText>(R.id.EmailId)
        val Password=findViewById<EditText>(R.id.Password)
        val SignUpBtn=findViewById<Button>(R.id.SignUpBtn)

        databaseReference=FirebaseDatabase.getInstance().getReference()

        val name= UserName.text.toString()
        val Email=UserEmail.text.toString()
        val password = Password.text.toString()
        val users = Users(name,Email,password)

        SignUpBtn.setOnClickListener {
//            if (name.isNullOrBlank()){
//                Toast.makeText(this,"Please Fill Every Detail Column",Toast.LENGTH_SHORT).show()
//
//            }else{
//                databaseReference.child("Users").setValue(users)
//                Toast.makeText(this,"Database Added",Toast.LENGTH_SHORT).show()
//            }
            databaseReference.child("Users").setValue(users)
            Toast.makeText(this,"Database Added",Toast.LENGTH_SHORT).show()


        }

    }
}