package com.example.changeyourhabit.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName= "points")
data class PointDate (
    @PrimaryKey(autoGenerate = true)
    var newPoindId:Long=0L,

    @ColumnInfo
    var numberOfPoints: Int=0,

    @ColumnInfo
    var date: String=" "
)

