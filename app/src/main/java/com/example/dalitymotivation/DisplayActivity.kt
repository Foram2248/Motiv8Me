package com.example.dalitymotivation

import android.annotation.SuppressLint
import android.media.RingtoneManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class DisplayActivity : AppCompatActivity() {

    private lateinit var quotesTextView: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        quotesTextView = findViewById(R.id.quotesTextView)
        val backButton = findViewById<Button>(R.id.backButton)

        // Get the quotes list and the new quote from the intent
        val quotesList = intent.getStringArrayListExtra("QUOTES_LIST")
        val newQuote = intent.getStringExtra("NEW_QUOTE")

        // Display the list of quotes
        quotesTextView.text = quotesList?.joinToString(separator = "\n\n")

        // Show the dialog box with the new quote
        if (newQuote != null) {
            showQuotePopup(newQuote)
        }

        // Set up the back button to return to the MainActivity
        backButton.setOnClickListener {
            finish()
        }
    }

    // Function to show a popup with the added quote
    private fun showQuotePopup(quote: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("New Motivational Quote Added")
        builder.setMessage(quote)
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()

        // Play a notification sound when the popup is shown
        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val ringtone = RingtoneManager.getRingtone(this, soundUri)
        ringtone.play()
    }
}
