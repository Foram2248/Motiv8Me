package com.example.dalitymotivation

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val quotes = mutableListOf("Believe in yourself and all that you are." , "Do the best you can until you know better. Then when you know better, do better.","Act as if what you do makes a difference. It does.","When the going gets tough, the tough get going")
    private lateinit var quotesTextView: TextView
    private lateinit var newQuoteEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        quotesTextView = findViewById(R.id.quotesTextView)
        newQuoteEditText = findViewById(R.id.newQuoteEditText)
        val sendDataButton = findViewById<Button>(R.id.sendDataButton)
        val menuButton = findViewById<ImageButton>(R.id.menuButton)

        // Initialize the TextView with the default quote
        updateQuotesTextView()

        sendDataButton.setOnClickListener {
            val newQuote = newQuoteEditText.text.toString()
            if (newQuote.isNotBlank()) {
                quotes.add(newQuote)
                newQuoteEditText.text.clear()
                updateQuotesTextView() // Update the TextView with the new quote

                val intent = Intent(this, DisplayActivity::class.java).apply {
                    putStringArrayListExtra("QUOTES_LIST", ArrayList(quotes))
                    putExtra("NEW_QUOTE", newQuote) // Pass the new quote to DisplayActivity
                }
                startActivity(intent)

                // Play notification sound and show message
                NotificationUtil.showNotification(this, newQuote)
            }
        }

        // Set up the menu button
        menuButton.setOnClickListener { view ->
            showPopupMenu(view)
        }
    }

    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.menuInflater.inflate(R.menu.menu_main, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menu_about -> {
                    val intent = Intent(this, AboutActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }

    // Function to update the TextView with the current list of quotes
    private fun updateQuotesTextView() {
        val quotesText = quotes.joinToString(separator = "\n\n")
        quotesTextView.text = quotesText
    }
}
