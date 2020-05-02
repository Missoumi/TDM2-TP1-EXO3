package com.example.myapplication

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.widget.addTextChangedListener
import androidx.room.Room
import com.example.exo2.Intervention
import java.util.*

class UpdateActivity : AppCompatActivity() {


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_avtivity)
        var db = Room.databaseBuilder(applicationContext, AppDB::class.java, "Intervention")
            .fallbackToDestructiveMigration().allowMainThreadQueries().build()

        var nom = intent.getStringExtra("nom")
        var type = intent.getStringExtra("type")
        var year = intent.getIntExtra("year", 2020)
        var month = intent.getIntExtra("month", 5)
        var day = intent.getIntExtra("day", 4)
        val id = intent.getIntExtra("id", 0)
        var text = findViewById<TextView>(R.id.text1)
        text.text = month.toString()
        var nomInput = findViewById<EditText>(R.id.nom_update)
        nomInput.setText(nom)
        nomInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                nom = s.toString()
            }
        })
        var typeInput = findViewById<EditText>(R.id.type_update)
        typeInput.setText(type)
        var dateInput = findViewById<Button>(R.id.update_date)
        typeInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                type = s.toString()
            }
        })
        dateInput.setOnClickListener {
            val dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDayOfMonth ->
                    year = mYear
                    month = mMonth
                    day = mDayOfMonth
                },
                year,
                month,
                day
            )
            dpd.show()
        }
        var update_button = findViewById<Button>(R.id.update_button)
        update_button.setOnClickListener {
            var intervention1 = Intervention()
            intervention1.id = id
            intervention1.date = Date(year, month, day)
            intervention1.nom = nom
            intervention1.type = type
            db.interventionDAO().updateIntervention(intervention1)
            finish()
        }
    }
}
