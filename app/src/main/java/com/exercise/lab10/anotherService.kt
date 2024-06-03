package com.exercise.lab10

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.exercise.lab10.databinding.ActivityAnotherServiceBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class anotherService : AppCompatActivity() {
    private lateinit var binding: ActivityAnotherServiceBinding
    private lateinit var db:FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAnotherServiceBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        db = Firebase.firestore

        readFireStoreData()

    }

    fun readFireStoreData(){
        db.collection("customer")
            .get()
            .addOnCompleteListener {
                val result:StringBuffer = StringBuffer()
                if(it.isSuccessful){
                    for(document in it.result!!){
                        val city = document.get("city") ?: "N/A"
                        val country = document.get("country") ?: "N/A"
                        val name = document.get("name") ?: "N/A"
                        val phone = document.get("phone") ?: "N/A"

                        //To do add new text view eg. resultTextView
                        result.append("Name: ").append(name).append("\n")
                            .append("Phone: ").append(phone).append("\n")
                            .append("City: ").append(city).append("\n")
                            .append("Country: ").append(country).append("\n").append("\n").append("\n")
                    }
                    binding.resultTextView.text = result.toString().trim()
                }
            }
    }
}