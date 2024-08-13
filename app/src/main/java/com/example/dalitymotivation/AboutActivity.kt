package com.example.dalitymotivation

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        // Set up the back button
        val backButton = findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener {
            // Finish the activity and return to the previous one
            finish()
        }
    }
}
