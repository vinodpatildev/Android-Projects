package com.example.birthdaygreet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class BirthdayGreetingActivity : AppCompatActivity() {

    companion object{
        const val NAME_EXTRA = "name_extra"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_birthday_greeting)

        val name = intent.getStringExtra(BirthdayGreetingActivity.NAME_EXTRA)

        val birthdayGreet = findViewById<TextView>(R.id.birthdayWisherTextView)

        birthdayGreet.setText("Happy Birthday \n$name!")

    }
}