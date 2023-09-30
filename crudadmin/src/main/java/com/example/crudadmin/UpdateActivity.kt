package com.example.crudadmin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.crudadmin.databinding.ActivityUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBinding
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.updateButton.setOnClickListener {
            val referenceID = binding.referenceID.text.toString()
            val updateItem = binding.updateItem.text.toString()
            val updateDescription = binding.updateDescription.text.toString()
            val updateLocation = binding.updateLocation.text.toString()
            updateData(referenceID,updateItem,updateDescription,updateLocation)
        }
    }
    private fun updateData(donationID: String, item: String, description: String, location: String) {
        database = FirebaseDatabase.getInstance().getReference("Users")
        val user = mapOf<String,String>(
            "item" to item,
            "description" to description,
            "location" to location
        )
        database.child(donationID).updateChildren(user).addOnSuccessListener {
            binding.referenceID.text.clear()
            binding.updateItem.text.clear()
            binding.updateDescription.text.clear()
            binding.updateLocation.text.clear()
            Toast.makeText(this,"Successfully Updated",Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(this,"Failed to Update",Toast.LENGTH_SHORT).show()
        }}
}