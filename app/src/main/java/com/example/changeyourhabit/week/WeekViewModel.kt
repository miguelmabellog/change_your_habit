package com.example.changeyourhabit.week

import android.app.Application
import androidx.lifecycle.*
import com.example.changeyourhabit.database.PointDao
import com.example.changeyourhabit.database.PointDate
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class WeekViewModel (
    val database: PointDao,
    application: Application
) : AndroidViewModel(application){
    private val allPoints=database.getPoints()
    private var numberOfDay:Int?=null
    private var dayOfMonth:Int?=null
    private val sdf = SimpleDateFormat("dd/M/yyyy")
    private val currentDate = sdf.format(Date())

    val mondayPoints: LiveData<String> = liveData{
            val data= putNumberForDay(0)?.numberOfPoints?.toString()
            emit(data?:"0")
    }

    val tuesdayPoints: LiveData<String> = liveData{
        val data= putNumberForDay(1)?.numberOfPoints?.toString()
        emit(data?:"0")
    }

    val wednesdayPoints: LiveData<String> = liveData{
        val data= putNumberForDay(2)?.numberOfPoints?.toString()
        emit(data?:"0")
    }

    val thursdayPoints: LiveData<String> = liveData{
        val data= putNumberForDay(3)?.numberOfPoints?.toString()
        emit(data?:"0")
    }

    val fridayPoints: LiveData<String> = liveData{
            val data= putNumberForDay(4)?.numberOfPoints?.toString()
            emit(data?:"0")
    }

    val saturdayPoints: LiveData<String> = liveData{
        val data= putNumberForDay(5)?.numberOfPoints?.toString()
        emit(data?:"0")
    }

    val sundayPoints: LiveData<String> = liveData{
        val data= putNumberForDay(6)?.numberOfPoints?.toString()
        emit(data?:"0")
    }

    private suspend  fun putPoints(tag: Int): PointDate? {
        var calendar: Calendar
        var key_search: String
        var result_search: PointDate?

        calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, -(numberOfDay!!-tag))
        key_search=sdf.format(calendar.time)
        result_search=database.getToday(key_search)

        return result_search
    }

    private suspend fun putNumberForDay(tag:Int): PointDate? {
            var todayDate=database.getToday(currentDate)
            numberOfDay = when (todayDate?.dayOfWeek){
                "Monday"-> 0
                "Tuesday"-> 1
                "Wednesday"-> 2
                "Thursday"-> 3
                "Friday"-> 4
                "Saturday"-> 5
                "Sunday"-> 6
                else->{
                    null
                }
            }
            return putPoints(tag)
    }





}