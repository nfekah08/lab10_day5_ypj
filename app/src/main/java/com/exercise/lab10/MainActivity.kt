package com.exercise.lab10

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.exercise.lab10.databinding.ActivityMainBinding
import com.exercise.lab10.databinding.ActivityServiceBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        binding.userLoginBtn.setOnClickListener {
            val intent = Intent(this, activityService::class.java)
            startActivity(intent)
        }

        binding.newUserBtn.setOnClickListener {
            val intent = Intent(this, activitySignup::class.java)
            startActivity(intent)
        }
    }
}