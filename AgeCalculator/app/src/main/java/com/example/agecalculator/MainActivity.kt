package com.example.agecalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var selected_date : TextView? = null;
    private var age_in_minutes : TextView? = null;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker = findViewById<Button>(R.id.btnDatePicker);
        selected_date = findViewById<TextView>(R.id.selected_date);
        age_in_minutes = findViewById<TextView>(R.id.age_in_minutes);

        btnDatePicker.setOnClickListener{
            datePicker();
        }
    }

    fun datePicker(){
        val cal = Calendar.getInstance();
        val year = cal.get(Calendar.YEAR);
        val month = cal.get(Calendar.MONTH);
        val day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{view, selectedYear, selectedMonth, selectedDay ->

                val date = "$selectedDay/${selectedMonth+1}/$selectedYear";
                selected_date?.setText(date);

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
                val dateObj = sdf.parse(date);

                val selectedDateInMinutes = dateObj.time / 60000;
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()));
                val currentDateInMinutes = currentDate.time / 60000;

                val diffMinutes = currentDateInMinutes - selectedDateInMinutes;

                age_in_minutes?.setText("${diffMinutes}");
            },
            year,
            month,
            day
        ).show()
    }
}