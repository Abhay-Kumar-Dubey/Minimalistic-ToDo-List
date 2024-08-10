package com.firefuro.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
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
        val hereText=findViewById<TextView>(R.id.clickHere)





        databaseReference=FirebaseDatabase.getInstance().getReference("Users")

        SignUpBtn.setOnClickListener {

            val name= UserName.text.toString()
            val Email= UserEmail.text.toString()
            val password = Password.text.toString()

            val users = Users(name,Email,password)

            if (name.isEmpty()|| Email.isEmpty() || password.isEmpty()){
                Toast.makeText(this,"Please Fill Every Detail Column",Toast.LENGTH_SHORT).show()

            }else{
                databaseReference.child(Email).setValue(users)
                Toast.makeText(this,"Database Added",Toast.LENGTH_SHORT).show()
                val intent = Intent(this,Sign_In_Screen::class.java)
                startActivity(intent)
            }


        }
        hereText.setOnClickListener{
            var intent = Intent(this,Sign_In_Screen::class.java)
            startActivity(intent)
        }


    }
}