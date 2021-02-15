package com.example.changeyourhabit.week

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.changeyourhabit.database.PointDao
import com.example.changeyourhabit.database.PointDate
import java.text.SimpleDateFormat
import java.util.*

class WeekViewModel (
    val database: PointDao,
    application: Application
) : AndroidViewModel(application){
    private var numberOfDay:Int?=null
    private val sdf = SimpleDateFormat("dd/M/yyyy")
    private val today=Date()
    var currentDate: String = sdf.format(today)
    private val searchDate=MutableLiveData<String>()

    val mondayPoints=searchDate.switchMap {
        liveData {
            var data= putNumberForDay(0,it)?.numberOfPoints?.toString()?:"0"
            emit(data)
        }
    }

    val tuesdayPoints=searchDate.switchMap {
        liveData {
            var data= putNumberForDay(1,it)?.numberOfPoints?.toString()?:"0"
            emit(data)
        }
    }

    val wednesdayPoints=searchDate.switchMap {
        liveData {
            var data= putNumberForDay(2,it)?.numberOfPoints?.toString()?:"0"
            emit(data)
        }
    }

    val thursdayPoints=searchDate.switchMap {
        liveData {
            var data= putNumberForDay(3,it)?.numberOfPoints?.toString()?:"0"
            emit(data)
        }
    }

    val fridayPoints=searchDate.switchMap {
        liveData {
            var data= putNumberForDay(4,it)?.numberOfPoints?.toString()?:"0"
            emit(data)
        }
    }


    val saturdayPoints=searchDate.switchMap {
        liveData {
            var data= putNumberForDay(5,it)?.numberOfPoints?.toString()?:"0"
            emit(data)
        }
    }


    val sundayPoints=searchDate.switchMap {
        liveData {
            var data= putNumberForDay(6,it)?.numberOfPoints?.toString()?:"0"
            emit(data)
        }
    }

    private val dayformat=SimpleDateFormat("EEEE")
    private var dayOfWeekSearch=dayformat.format(Date())

    private var calendar = Calendar.getInstance()
    private var daySearch = calendar[Calendar.DAY_OF_MONTH]
    private var monthSearch=calendar[Calendar.MONTH]+1
    private var yearSearch=calendar[Calendar.YEAR]




    fun givedate(day:Int,month:Int,year:Int){
        val dateUpdate="$day/${month+1}/$year"


        calendar.set(Calendar.DAY_OF_MONTH,day)
        calendar.set(Calendar.MONTH,month)
        calendar.set(Calendar.YEAR,year)


        dayOfWeekSearch=dayformat.format(calendar.time)

        searchDate.value=dateUpdate
        daySearch=day
        monthSearch=month+1
        yearSearch=year

        refreshDateText(daySearch,monthSearch,yearSearch,dayOfWeekSearch,calendar)
    }

    init {
        searchDate.value=currentDate
        refreshDateText(daySearch,monthSearch,yearSearch,dayOfWeekSearch,calendar)
    }

    private var _mondayDate=MutableLiveData<String>()
    val  mondayDate: LiveData<String>
            get()=_mondayDate

    private var _tuesdayDate=MutableLiveData<String>()
    val  tuesdayDate: LiveData<String>
        get()=_tuesdayDate

    private var _wednesdayDate=MutableLiveData<String>()
    val  wednesdayDate: LiveData<String>
        get()=_wednesdayDate

    private var _thursdayDate=MutableLiveData<String>()
    val  thursdayDate: LiveData<String>
        get()=_thursdayDate

    private var _fridayDate=MutableLiveData<String>()
    val  fridayDate: LiveData<String>
        get()=_fridayDate

    private var _saturdayDate=MutableLiveData<String>()
    val  saturdayDate: LiveData<String>
        get()=_saturdayDate

    private var _sundayDate=MutableLiveData<String>()
    val  sundayDate: LiveData<String>
        get()=_sundayDate

    fun refreshDateText(day:Int, month: Int, year: Int, dayOfWeek: String, calendar: Calendar){

        when (dayOfWeek){
            "Monday"->{ _mondayDate.value=sdf.format(calendar.time)
                _tuesdayDate.value=sdf.format(addCalendar(1,calendar).time)
                _wednesdayDate.value=sdf.format(addCalendar(2,calendar).time)
                _thursdayDate.value=sdf.format(addCalendar(3,calendar).time)
                _fridayDate.value=sdf.format(addCalendar(4,calendar).time)
                _saturdayDate.value=sdf.format(addCalendar(4,calendar).time)
                _sundayDate.value=sdf.format(addCalendar(5,calendar).time)

                        }

        }

    }

    fun addCalendar(add:Int, calendar: Calendar):Calendar{
        calendar.add(Calendar.DATE, +(add))
        return calendar
    }






    private suspend  fun putPoints(tag: Int): PointDate? {
        var calendar: Calendar
        var key_search: String
        var result_search: PointDate?=null

        if(numberOfDay != null){
            calendar = Calendar.getInstance()
            calendar.add(Calendar.DATE, -(numberOfDay!! -tag))
            key_search=sdf.format(calendar.time)
            result_search=database.getToday(key_search)
        }


        return result_search
    }

    private suspend fun putNumberForDay(tag:Int, searchDate:String): PointDate? {
        Log.i("PutNumberforDay",searchDate)
            var todayDate=database.getToday(searchDate)

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