package com.example.myapplication

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.exo2.DateConverter
import com.example.exo2.Intervention

@Database (entities = [(Intervention::class)], version = 2)
@TypeConverters(DateConverter::class)
abstract class AppDB : RoomDatabase() {
    abstract fun interventionDAO(): Intervention_DAO
}