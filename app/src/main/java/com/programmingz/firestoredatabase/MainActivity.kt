package com.programmingz.firestoredatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var etName: EditText
    private lateinit var etAddress: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnSave: Button
    private lateinit var btnShow: Button
    private lateinit var btnUpdated: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var btnDeleted: Button


    private var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etName = findViewById(R.id.tvName)
        etAddress = findViewById(R.id.tvAddress)
        etEmail = findViewById(R.id.tvEmail)
        etPassword = findViewById(R.id.tvPassword)
        btnSave = findViewById(R.id.btnSave)
        btnShow = findViewById(R.id.btnShow)
        btnUpdated = findViewById(R.id.btnUpdated)
        btnDeleted = findViewById(R.id.btnDeleted)


        btnDeleted.setOnClickListener {
            startActivity(Intent(this, DeleteActivity::class.java))
        }

        btnUpdated.setOnClickListener {
            startActivity(Intent(this, UpdateActivity::class.java))
        }

        btnShow.setOnClickListener {
            startActivity(Intent(this, FetcbDataActivity::class.java))
        }



        btnSave.setOnClickListener {
            val sName = etName.text.toString().trim()
            val sAddress = etAddress.text.toString().trim()
            val sEmail = etEmail.text.toString().trim()
            val sPassword = etPassword.text.toString().trim()

            val userMap = hashMapOf(
                "name" to sName,
                "address" to sAddress,
                "email" to sEmail,
                "password" to sPassword
            )

            val user = FirebaseAuth.getInstance().currentUser
            if (user != null) {
                Toast.makeText(this, "some error", Toast.LENGTH_SHORT).show()

            } else {
                    // Executes 5 times: i takes values from 1 to 5
                    val userId = user?.uid
            }

            db.collection("User").document(user.toString()).set(userMap)
                .addOnSuccessListener {
                Toast.makeText(this, "Successfully updated", Toast.LENGTH_SHORT).show()
                    etName.text.clear()
                    etAddress.text.clear()
                    etEmail.text.clear()
                    etPassword.text.clear()
            }

                .addOnFailureListener {
                Toast.makeText(this, "Some error occure", Toast.LENGTH_SHORT).show()
            }


        }
    }
}