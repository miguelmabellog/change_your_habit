package com.example.changeyourhabit.week

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.changeyourhabit.addNew.AddNewViewModel
import com.example.changeyourhabit.database.PointDao

class WeekViewModelFactory (
    private val dataSource: PointDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeekViewModel::class.java)) {
            return WeekViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}