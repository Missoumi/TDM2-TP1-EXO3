package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.exo2.Intervention
import com.example.exo2.InterventionView


class MainActivity : AppCompatActivity() {
    val FILE_NAME = "inter2.json"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listIntervention = findViewById<LinearLayout>(R.id.interventionList)
        var db = Room.databaseBuilder(applicationContext, AppDB::class.java, "Intervention").fallbackToDestructiveMigration().allowMainThreadQueries().build()

//        var intervention1 = Intervention()
//        intervention1.id = 9
//        intervention1.date = Date(201, 5, 14)
//        intervention1.nom = "Missoumi"
//        intervention1.type = "Type 3"
        Thread {
//            db.interventionDAO().saveInternetion(intervention1)
            val list:List<Intervention> =   db.interventionDAO().interventionsAll()
            this@MainActivity.runOnUiThread(java.lang.Runnable {
                list.forEach {inter ->
                    listIntervention.addView(InterventionView(this,
                        _id = inter.id,
                        _date = inter.date,
                        _nom = inter.nom,
                        _type = inter.type
                    ))
                }
            })

            listIntervention.removeAllViews()
        }.start()

    }

    private val isExternalStorageAvailable: Boolean
        get() {
            val extStorageState = Environment.getExternalStorageState()
            return Environment.MEDIA_MOUNTED.equals(extStorageState)
        }

    override fun onResume() {
        super.onResume()
        var db = Room.databaseBuilder(applicationContext, AppDB::class.java, "Intervention").fallbackToDestructiveMigration().allowMainThreadQueries().build()
        val listIntervention = findViewById<LinearLayout>(R.id.interventionList)
        listIntervention.removeAllViews()
        val list:List<Intervention> =   db.interventionDAO().interventionsAll()
        list.forEach {inter ->
            listIntervention.addView(InterventionView(this,
                _id = inter.id,
                _date = inter.date,
                _nom = inter.nom,
                _type = inter.type
            ))
        }
    }
}
