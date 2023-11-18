package com.programmingz.firestoredatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FetcbDataActivity : AppCompatActivity() {
    private lateinit var tvName: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvAddress: TextView

    private var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetcb_data)

        tvAddress = findViewById(R.id.address)
        tvEmail = findViewById(R.id.email)
        tvName = findViewById(R.id.name)

/*
        val userId = FirebaseAuth.getInstance().currentUser!!.uid
*/
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            Toast.makeText(this, "some error", Toast.LENGTH_SHORT).show()

        } else {
            // Executes 5 times: i takes values from 1 to 5
            val userId = user?.uid
        }

        val ref = db.collection("User").document(user.toString())
        ref.get().addOnSuccessListener {
            if (it!=null){
                val name = it.data?.get("name")?.toString()
                val address = it.data?.get("address")?.toString()
                val email = it.data?.get("email")?.toString()

                tvName.text = name
                tvAddress.text = address
                tvEmail.text = email
            }
        }
            .addOnFailureListener {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
    }
}