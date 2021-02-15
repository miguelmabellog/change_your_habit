package com.example.changeyourhabit.week

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.changeyourhabit.R
import com.example.changeyourhabit.database.PointDatabase
import com.example.changeyourhabit.databinding.WeekFragmentBinding
import java.util.*

class WeekFragment : Fragment() {



    private lateinit var viewModel: WeekViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<WeekFragmentBinding>(
            inflater, R.layout.week_fragment, container, false)
        val application = requireNotNull(this.activity).application
        val dataSource = PointDatabase.getInstance(application).pointDao
        val viewModelFactory = WeekViewModelFactory(dataSource, application)

        viewModel = ViewModelProvider(this,viewModelFactory).get(WeekViewModel::class.java)
        binding.viewModel=viewModel

        binding.lifecycleOwner=this

        binding.button.setOnClickListener {
            showDatePickerDialogo()
        }

        return binding.root
    }

    private fun showDatePickerDialogo() {
        val datePicker=DatePickerFragment{day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(childFragmentManager,"datePicker")
    }

    private fun onDateSelected(day:Int, month:Int, year:Int) {
        Log.i("WeekDialogoFragment", "$day , $month, $year")
        val cal: Calendar = Calendar.getInstance()
        viewModel.givedate(day,month,year)

    }


}