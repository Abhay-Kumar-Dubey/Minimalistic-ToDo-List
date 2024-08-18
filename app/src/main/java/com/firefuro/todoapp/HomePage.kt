package com.firefuro.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class HomePage : AppCompatActivity() {
    lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        val NameText = findViewById<TextView>(R.id.nameText)
        val UserIntent=intent.getStringExtra("UserID")

        databaseReference= FirebaseDatabase.getInstance().getReference("Users")

        UserIntent?.let { databaseReference.child(it).get().addOnSuccessListener {
            if (it.exists()){
                val name = it.child("nameData").value
                NameText.text= name.toString()
            }
        } }
    }
}