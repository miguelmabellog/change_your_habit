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

    private val dayformat=SimpleDateFormat("EEEE")
    private var dayOfWeekSearch=dayformat.format(Date())

    private var calendar = Calendar.getInstance()
    private var daySearch = calendar[Calendar.DAY_OF_MONTH]
    private var monthSearch=calendar[Calendar.MONTH]+1
    private var yearSearch=calendar[Calendar.YEAR]

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







    private var _mondayDate=MutableLiveData<String?>()
    val  mondayDate: LiveData<String?>
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

        refreshDateText(dayOfWeekSearch)
    }

    init {

        searchDate.value=currentDate
        refreshDateText(dayOfWeekSearch)

    }



    fun refreshDateText( dayOfWeek: String){
        when (dayOfWeek){
            "Monday"->fillTextDate(0)
            "Tuesday"->fillTextDate(1)
            "Wednesday"->fillTextDate(2)
            "Thursday"->fillTextDate(3)
            "Friday"->fillTextDate(4)
            "Saturday"->fillTextDate(5)
            "Sunday"->fillTextDate(6)
        }

    }

    fun fillTextDate(key:Int){

        var arrayname = Array(7) { _ ->  1 }
        arrayname[0]=-key

        var innercalendario=calendar

        val lastDay=innercalendario[Calendar.DAY_OF_MONTH]
        val lastMonth=innercalendario[Calendar.MONTH]
        val lastYear=innercalendario[Calendar.YEAR]

        _mondayDate.value=sdf.format(addCalendar(arrayname[0],innercalendario).time)
        _tuesdayDate.value=sdf.format(addCalendar(arrayname[1],innercalendario).time)
        _wednesdayDate.value=sdf.format(addCalendar(arrayname[2],innercalendario).time)
        _thursdayDate.value=sdf.format(addCalendar(arrayname[3],innercalendario).time)
        _fridayDate.value=sdf.format(addCalendar(arrayname[4],innercalendario).time)
        _saturdayDate.value=sdf.format(addCalendar(arrayname[5],innercalendario).time)
        _sundayDate.value=sdf.format(addCalendar(arrayname[6],innercalendario).time)

        calendar.set(Calendar.DAY_OF_MONTH,lastDay)
        calendar.set(Calendar.MONTH,lastMonth)
        calendar.set(Calendar.YEAR,lastYear)

    }

    fun addCalendar(add:Int, innercalendar: Calendar):Calendar{
        innercalendar.add(Calendar.DATE , +(add))
        return innercalendar
    }






    private suspend  fun putPoints(tag: Int): PointDate? {
        var innerC=calendar
        Log.i("calendar",sdf.format(calendar.time))
        val lastDay=innerC[Calendar.DAY_OF_MONTH]
        val lastMonth=innerC[Calendar.MONTH]
        val lastYear=innerC[Calendar.YEAR]
        var key_search: String=""
        var result_search: PointDate?=null

        if(numberOfDay != null){
            innerC.add(Calendar.DATE, -(numberOfDay!! -tag))
            key_search=sdf.format(innerC.time)
            Log.i("keay_search",key_search)
            calendar.set(Calendar.DAY_OF_MONTH,lastDay)
            calendar.set(Calendar.MONTH,lastMonth)
            calendar.set(Calendar.YEAR,lastYear)
        }
        result_search=database.getToday(key_search)


        return result_search

    }

    private suspend fun putNumberForDay(tag:Int, searchDate:String): PointDate? {



            numberOfDay = when (dayOfWeekSearch){
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