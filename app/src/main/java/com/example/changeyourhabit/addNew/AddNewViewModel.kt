package com.example.changeyourhabit.addNew

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.preference.PreferenceManager
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
    private val dayformat=SimpleDateFormat("EEEE")
    private val currentDay=dayformat.format(Date())
    private val houformat=SimpleDateFormat("hh:mm")

    val preferences = PreferenceManager.getDefaultSharedPreferences(application)
    val editor=preferences.edit()

    val todayPoint:LiveData<Int>
    get()=_todayPoint

    private var _todayPoint=MutableLiveData(0)


    val lastHour:LiveData<String>
        get()=_lastHour

    private var _lastHour=MutableLiveData(" ")

    val lastDate:LiveData<String>
        get()=_lastDate

    private var _lastDate=MutableLiveData(" ")

    init {
        checktoday()
        _lastHour.value=preferences.getString("KEY_RECENT_HOUR","")
        _lastDate.value=preferences.getString("KEY_RECENT_DATE","")
    }

    private fun checktoday() {
        viewModelScope.launch {
            val todayDate=database.getToday(currentDate)
            if(todayDate==null){
                setPoint()
            }else{
                _todayPoint.value=todayDate.numberOfPoints
            }
        }
    }

    private fun setPoint() {
        viewModelScope.launch {
            val newPointDate = PointDate(numberOfPoints = 0,date =currentDate,dayOfWeek = currentDay)
            database.insert(newPointDate)

        }
    }

    fun addPoint(){
        viewModelScope.launch {
            var todayDate=database.getToday(currentDate)
            if(todayDate!=null){
                todayDate.numberOfPoints=todayDate.numberOfPoints+1
                database.update(todayDate)
                _todayPoint.value=todayDate.numberOfPoints
                val currenthour=houformat.format(Date())
                editor.putString("KEY_RECENT_HOUR",currenthour)
                editor.putString("KEY_RECENT_DATE",currentDate)
                editor.apply()
                _lastHour.value=currenthour
                _lastDate.value=currentDate
            }

        }
    }

    fun subtractPoint(){
        viewModelScope.launch {
            var todayDate=database.getToday(currentDate)
            if(todayDate!=null){
                if(todayDate.numberOfPoints>0){
                    todayDate.numberOfPoints=todayDate.numberOfPoints-1
                }
                database.update(todayDate)
                _todayPoint.value=todayDate.numberOfPoints
                _lastHour.value=""
                _lastDate.value=""
            }
        }
    }
}