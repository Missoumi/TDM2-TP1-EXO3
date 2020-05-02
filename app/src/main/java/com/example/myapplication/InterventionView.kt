package com.example.exo2

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.example.myapplication.R
import com.example.myapplication.UpdateActivity
import java.util.*
import kotlin.reflect.KFunction0

class InterventionView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0,
    _id: Int,
    _date: Date,
    _nom:String,
    _type:String
    ) : LinearLayout(context, attrs, defStyle, defStyleRes) {
    init {
        LayoutInflater.from(context).inflate(R.layout.intervention_view, this, true)
        var idView = findViewById<TextView>(R.id.id)
        idView.text = _id.toString()
        var dateView = findViewById<TextView>(R.id.date)
        dateView.text = _date.toString()
        var nomView = findViewById<TextView>(R.id.nom)
        nomView.text = _nom
        var typeView = findViewById<TextView>(R.id.type)
        typeView.text = _type
        var button = findViewById<Button>(R.id.updateButton)
        button.setOnClickListener {
            val intent = Intent(context, UpdateActivity::class.java)
            intent.putExtra("nom", _nom)
            intent.putExtra("id", _id)
            intent.putExtra("type", _type)
            intent.putExtra("day", _date.day)
            intent.putExtra("year", _date.year)
            intent.putExtra("month", _date.month)
            context.startActivity(intent)


        }
    }
}