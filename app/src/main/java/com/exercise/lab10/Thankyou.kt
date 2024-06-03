package com.exercise.lab10

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.exercise.lab10.databinding.ActivityThankyouBinding

class Thankyou : AppCompatActivity() {

    private lateinit var binding:ActivityThankyouBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityThankyouBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        binding.thankyouPortalBtn.setOnClickListener {
            val intent = Intent(this, activityService::class.java)
            startActivity(intent)
        }

    }
}