package com.firefuro.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HomePage : AppCompatActivity() {
    lateinit var databaseReference: DatabaseReference
    lateinit var taskArrayList:ArrayList<Tasks>
    lateinit var RecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        val NameText = findViewById<TextView>(R.id.nameText)
        val UserIntent=intent.getStringExtra("UserID")
        taskArrayList= ArrayList()
        RecyclerView=findViewById(R.id.TaskList)
        databaseReference= FirebaseDatabase.getInstance().getReference("Users")

        UserIntent?.let { databaseReference.child(it).get().addOnSuccessListener {
            if (it.exists()){
                val name = it.child("nameData").value
                NameText.text= name.toString()
            }
        } }
        RecyclerView.adapter=MyAdapter(taskArrayList)
        fetchdata()
    }
//
     private fun fetchdata() {
//         val UserIntent=intent.getStringExtra("UserID").toString()

         databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                taskArrayList.clear()
                if (snapshot.exists()){
                for (userSnapshot in snapshot.children) {
                    val user = userSnapshot.getValue(Tasks::class.java)
                    taskArrayList.add(user!!)

                }
                    RecyclerView.adapter=MyAdapter(taskArrayList)

            }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}