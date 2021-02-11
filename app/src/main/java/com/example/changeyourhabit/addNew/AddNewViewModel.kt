package com.example.changeyourhabit.addNew

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.changeyourhabit.database.PointDao
import com.example.changeyourhabit.database.PointDate
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class AddNewViewModel(
    val database: PointDao,
    application: Application) : AndroidViewModel(application){

    fun setPoint() {

        viewModelScope.launch {

            //val newPointDate = PointDate(numberOfPoints = 1,date ="Date()")
            //database.insert(newPointDate)

        }


    }
}