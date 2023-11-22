package com.programmingz.firestoredatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DeleteActivity : AppCompatActivity() {
    private lateinit var etName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etAddress: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnDelete: Button

    private var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete)

        etName = findViewById(R.id.etName)
        etEmail = findViewById(R.id.etEmail)
        etAddress = findViewById(R.id.etAddress)
        etPassword = findViewById(R.id.etPassword)
        btnDelete = findViewById(R.id.btnDelete)

        setData()

        btnDelete.setOnClickListener {
            val mapDelete = mapOf(
                "password" to FieldValue.delete()
            )
            val user = FirebaseAuth.getInstance().currentUser
            if (user!=null){
                Toast.makeText(this, "some error occures", Toast.LENGTH_SHORT).show()
            } else {
                val userId = user?.uid
            }

            db.collection("User").document(user.toString()).update(mapDelete)
                .addOnSuccessListener {
                    Toast.makeText(this,  "Successfully deleted", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Deletion failed! ", Toast.LENGTH_SHORT).show()
                }
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