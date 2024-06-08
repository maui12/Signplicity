package com.emprendimiento.signplicity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.emprendimiento.signplicity.databinding.ActivitySeeUserBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SeeUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySeeUserBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeeUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userId = intent.getStringExtra("userId")

        databaseReference = FirebaseDatabase.getInstance().getReference("Users")

        if (userId != null) {
            databaseReference.child(userId).get().addOnSuccessListener {
                if(it.exists()) {
                    val username = findViewById<TextView>(R.id.tvUsername2)
                    val email = findViewById<TextView>(R.id.tvEmail2)
                    val phone = findViewById<TextView>(R.id.tvPhone2)
                    val bio = findViewById<TextView>(R.id.tvBio2)

                    username.text = it.child("username").value.toString()
                    email.text = it.child("email").value.toString()
                    phone.text = it.child("phone").value.toString()
                    bio.text = it.child("bio").value.toString()

                }
            }
        }

    }
}