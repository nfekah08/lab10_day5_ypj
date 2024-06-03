package com.exercise.lab10

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.exercise.lab10.databinding.ActivityServiceBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class activityService : AppCompatActivity() {

    private lateinit var binding: ActivityServiceBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  enableEdgeToEdge()

        binding = ActivityServiceBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //dapatkan connection ke database
        auth = FirebaseAuth.getInstance()

        binding.loginBtn.setOnClickListener {
            if(binding.emailLoginEditText.text.trim().toString().isNotEmpty() ||
                binding.pwdLoginEditText.text.trim().toString().isNotEmpty()){
                loginUser(binding.emailLoginEditText.text.toString(),
                    binding.pwdLoginEditText.text.toString())
            } else{
                Snackbar.make(binding.root,
                    "Please check your username and password",
                    Snackbar.LENGTH_LONG).show()
            }
        }

    }

    fun loginUser(email:String, password:String){

        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
            task ->
            if(task.isSuccessful){
                val intent = Intent(this,anotherService::class.java)
                startActivity(intent)
            } else{
                Snackbar.make(binding.root,
                    "Please check your username and password",
                    Snackbar.LENGTH_LONG).show()

            }
        }
        //cara dapatkan error message
            .addOnFailureListener { err->
                Log.d("debug",err.toString())
                Snackbar.make(binding.root,
                    err.message!!,
                    Snackbar.LENGTH_LONG
                ).show()
            }
    }
}