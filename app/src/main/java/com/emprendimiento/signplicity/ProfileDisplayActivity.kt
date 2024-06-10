package com.emprendimiento.signplicity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.emprendimiento.signplicity.databinding.ActivityProfileDisplayBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class ProfileDisplayActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileDisplayBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var storageReference: StorageReference
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityProfileDisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            root.findViewById<Button>(R.id.editBtn).setOnClickListener {
                val intent = Intent(root.context,ProfileActivity::class.java)
                root.context.startActivity(intent)
            }
        }
        auth = FirebaseAuth.getInstance()
        val uid = auth.currentUser?.uid
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        storageReference = FirebaseStorage.getInstance().getReference("Users/$uid")

        if (uid != null) {
            getUserData(uid)
        }

        mAuth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)


        val auth = Firebase.auth
        val user = auth.currentUser

        val sign_out_button = findViewById<Button>(R.id.logout_button)
        sign_out_button.setOnClickListener {
            signOutAndStartSignInActivity()
        }
    }

    private fun signOutAndStartSignInActivity() {
        mAuth.signOut()

        mGoogleSignInClient.signOut().addOnCompleteListener(this) {
            // Optional: Update UI or show a message to the user
            val intent = Intent(this@ProfileDisplayActivity, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun getUserData(uid: String) {
        databaseReference.child(uid).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val username = dataSnapshot.child("username").getValue(String::class.java)
                val email = dataSnapshot.child("email").getValue(String::class.java)
                val phone = dataSnapshot.child("phone").getValue(String::class.java)
                val bio = dataSnapshot.child("bio").getValue(String::class.java)

                binding.tvUsername.text = username
                binding.tvEmail.text = email
                binding.tvPhone.text = phone
                binding.tvBio.text = bio

                // Cargar la imagen de perfil si existe
                storageReference.downloadUrl.addOnSuccessListener { uri ->
                    Glide.with(this@ProfileDisplayActivity)
                        .load(uri)
                        .into(binding.profileImage)
                }.addOnFailureListener {
                    // En caso de error, dejar la imagen por defecto
                    binding.profileImage.setImageResource(R.drawable.profile_default_pic)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@ProfileDisplayActivity, "Error al cargar los datos", Toast.LENGTH_SHORT).show()
            }
        })
    }
}