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

class UpdateActivity : AppCompatActivity() {
    private lateinit var etName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etAddress: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnUpdate: Button

    private var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        etName = findViewById(R.id.etName)
        etEmail = findViewById(R.id.etEmail)
        etAddress = findViewById(R.id.etAddress)
        etPassword = findViewById(R.id.etPassword)
        btnUpdate = findViewById(R.id.btnUpdate)

        setData()

        btnUpdate.setOnClickListener {
            val sName = etName.text.toString()
            val sAddress = etAddress.text.toString()
            val sEmail = etEmail.text.toString()
            val sPassword = etPassword.text.toString()

            val updateMap = mapOf(
                "name" to sName,
                "email" to sEmail,
                "password" to sPassword,
                "address" to sAddress
            )
            val user = FirebaseAuth.getInstance().currentUser
            if (user!=null){
                Toast.makeText(this, "some error occures", Toast.LENGTH_SHORT).show()
            } else {
                val userId = user?.uid
            }
            db.collection("User").document(user.toString()).update(updateMap)

            Toast.makeText(this, "Successfully Updated", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun setData() {
        val user = FirebaseAuth.getInstance().currentUser
        if (user!=null){
            Toast.makeText(this, "some error occures", Toast.LENGTH_SHORT).show()
        } else {
            val userId = user?.uid
        }

        val ref = db.collection("User").document(user.toString())
        ref.get().addOnSuccessListener {
            if (it!=null){
                val name = it.data?.get("name")?.toString()
                val email = it.data?.get("email")?.toString()
                val address = it.data?.get("address")?.toString()
                val password = it.data?.get("password")?.toString()

                etName.setText(name)
                etAddress.setText(address)
                etEmail.setText(email)
                etPassword.setText(password)
            }
        }
            .addOnFailureListener {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
    }

}