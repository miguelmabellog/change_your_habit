package com.example.changeyourhabit.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PointDao {

    @Insert
    fun insert(pointDate: PointDate)

    @Update
    suspend fun update(pointDate: PointDate)

    @Delete
    suspend fun delete(pointDate: PointDate)

    @Query("SELECT * FROM points ORDER BY date ")
    suspend fun getPoints(): LiveData<PointDate>
}