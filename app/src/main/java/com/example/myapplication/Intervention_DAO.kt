package com.example.myapplication

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.exo2.Intervention

@Dao
interface Intervention_DAO {

    @Insert
    fun saveInternetion(intervention: Intervention)

    @Query("SELECT * FROM  intervention ")
    fun interventionsAll(): List<Intervention>

    @Update
    fun updateIntervention(intervention: Intervention)
}