package com.example.crudadmin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.crudadmin.databinding.ActivityUploadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UploadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUploadBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.saveButton.setOnClickListener {

            val donationID = binding.uploadID.text.toString()
            val item = binding.uploadItem.text.toString()
            val description = binding.uploadDescription.text.toString()
            val location = binding.uploadLocation.text.toString()

            database = FirebaseDatabase.getInstance().getReference("Users")
            val users = DonationData(donationID,item,description,location)
            database.child(donationID).setValue(users).addOnSuccessListener {
                binding.uploadID.text.clear()
                binding.uploadItem.text.clear()
                binding.uploadDescription.text.clear()
                binding.uploadLocation.text.clear()
                Toast.makeText(this,"Saved",Toast.LENGTH_SHORT).show()
                val intent = Intent(this@UploadActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener{
                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
            }
        }
    }
}