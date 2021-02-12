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
    private val sdf = SimpleDateFormat("dd/M/yyyy")
    private val currentDate = sdf.format(Date())
    private lateinit var todayDate: PointDate

    init {
        checktoday()
    }

    private fun checktoday() {
        viewModelScope.launch {
            val todayDate=database.getToday(currentDate)
            if(todayDate==null){
                setPoint()
            }
        }
    }

    private fun setPoint() {
        viewModelScope.launch {
            val newPointDate = PointDate(numberOfPoints = 1,date =currentDate)
            database.insert(newPointDate)
        }
    }

    fun addPoint(){
        viewModelScope.launch {
            var todayDate=database.getToday(currentDate)
            if(todayDate!=null){
                todayDate.numberOfPoints=todayDate.numberOfPoints+1
                database.update(todayDate)
            }


        }
    }
}