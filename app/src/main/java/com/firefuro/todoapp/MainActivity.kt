package com.firefuro.todoapp

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    lateinit var databaseReference: DatabaseReference
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val UserName=findViewById<EditText>(R.id.UserName)
        val UserEmail=findViewById<EditText>(R.id.EmailId)
        val Password=findViewById<EditText>(R.id.Password)
        val SignUpBtn=findViewById<Button>(R.id.SignUpBtn)
        val hereText=findViewById<TextView>(R.id.clickHere)
        auth= FirebaseAuth.getInstance()





//        databaseReference=FirebaseDatabase.getInstance().getReference("Users")

        SignUpBtn.setOnClickListener {

            val name= UserName.text.toString()
            val Email= UserEmail.text.toString()
            val password = Password.text.toString()

            val users = Users(name,Email,password)

            if (name.isEmpty()|| Email.isEmpty() || password.isEmpty()){
                Toast.makeText(this,"Please Fill Every Detail Column",Toast.LENGTH_SHORT).show()

            }else{
//                databaseReference.child(Email).setValue(users)
                auth.createUserWithEmailAndPassword(Email, password)
                    .addOnCompleteListener(this){ task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            val intent = Intent(this,Sign_In_Screen::class.java)
                            startActivity(intent)

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(
                                baseContext,
                                "Authentication failed.",
                                Toast.LENGTH_SHORT,
                            ).show()
                        }
                    }
//                Toast.makeText(this,"Database Added",Toast.LENGTH_SHORT).show()

            }


        }
        hereText.setOnClickListener{
            var intent = Intent(this,Sign_In_Screen::class.java)
            startActivity(intent)
        }




    }
}