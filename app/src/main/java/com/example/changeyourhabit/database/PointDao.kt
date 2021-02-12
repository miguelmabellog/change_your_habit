package com.example.changeyourhabit.database

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface PointDao {

    @Insert
    suspend fun insert(pointDate: PointDate)

    @Update
    suspend fun update(pointDate: PointDate)

    @Delete
    suspend fun delete(pointDate: PointDate)

    @Query("Select * FROM points WHERE date = :key ")
    suspend fun getToday(key:String): PointDate?

    @Query("SELECT * FROM points ")
    fun getPoints(): LiveData<List<PointDate?>>
}