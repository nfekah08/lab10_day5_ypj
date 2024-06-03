package com.exercise.lab10

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.exercise.lab10.databinding.ActivitySignupBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.ktx.firestore
import kotlin.math.log

class activitySignup : AppCompatActivity() {
    private lateinit var binding:ActivitySignupBinding

    private lateinit var auth: FirebaseAuth

    private lateinit var db:FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // enableEdgeToEdge()


        binding = ActivitySignupBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = FirebaseAuth.getInstance()

        binding.signupCreateBtn.setOnClickListener {
            createUser(binding.signupEmailEditText.text.toString(),
                binding.signupPwdEditText.text.toString())
        }

        db = Firebase.firestore
    }

    fun createUser(email:String, password:String){

        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
            task ->

            if (task.isSuccessful){
                newCustomer()
                val intent = Intent(this,Thankyou::class.java)
                startActivity(intent)
            } else{
                Snackbar.make(
                    binding.root,
                    "Enter a valid username and password",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun newCustomer(){
        //key value based on data type

        val customer = hashMapOf(
            "name" to binding.fullNameEditText.text.toString().trim(),
            "city" to binding.cityEditText.text.toString().trim(),
            "country" to binding.countryEditText.text.toString().trim(),
            "phone" to binding.phoneNoEditText.text.toString().trim()
        )

        db.collection("customer")
            .add(customer)
            .addOnSuccessListener {
                documentReference ->
                Log.d("debug", "Document successfully added with id ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.d("debug","An error happen ${e.message}")
            }
    }



}