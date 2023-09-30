package com.example.crudadmin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.crudadmin.databinding.ActivityDeleteBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DeleteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDeleteBinding
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.deleteButton.setOnClickListener {
            val donationID = binding.deleteID.text.toString()
            if (donationID.isNotEmpty())
                deleteData(donationID)
            else
                Toast.makeText(this, "Please enter the Donation ID", Toast.LENGTH_SHORT).show()
        }
    }
    private fun deleteData(donationID: String){
        database = FirebaseDatabase.getInstance().getReference("Users")
        database.child(donationID).removeValue().addOnSuccessListener {
            binding.deleteID.text.clear()
            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Unable to delete", Toast.LENGTH_SHORT).show()
        }
    }
}