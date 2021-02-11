package com.example.changeyourhabit.week

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.changeyourhabit.database.PointDao

class WeekViewModel (
    val database: PointDao,
    application: Application
) : AndroidViewModel(application){

}