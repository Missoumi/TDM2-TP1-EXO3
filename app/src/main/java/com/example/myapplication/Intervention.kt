package com.example.exo2
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.time.OffsetDateTime
import java.util.*

class DateConverter {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

}

@Entity(tableName = "intervention")
class Intervention {

    @PrimaryKey
    var id:Int = 0

    var date: Date = Date()
    var nom : String = ""
    var type : String = ""
}