package com.example.crudadmin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.crudadmin.databinding.ActivitySearchBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.searchButton.setOnClickListener {
            val searchID : String = binding.searchDonationID.text.toString()
            if  (searchID.isNotEmpty()){
                readData(searchID)
            }else{
                Toast.makeText(this,"PLease enter the Donation ID",Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun readData(donationID: String) {

        database = FirebaseDatabase.getInstance().getReference("Users")
        database.child(donationID).get().addOnSuccessListener {

            if (it.exists()){
                val item = it.child("item").value
                val description = it.child("description").value
                val location = it.child("location").value
                Toast.makeText(this,"Results Found",Toast.LENGTH_SHORT).show()
                binding.searchDonationID.text.clear()
                binding.readItem.text = item.toString()
                binding.readDescription.text = description.toString()
                binding.readLocation.text = location.toString()
            }else{
                Toast.makeText(this,"Donation ID does not exist",Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(this,"Something went wrong",Toast.LENGTH_SHORT).show()
        }
    }
}