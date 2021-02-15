package com.example.changeyourhabit.week

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
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


        viewModel.highligh.observe(viewLifecycleOwner,Observer{

            binding.mondayConstrain.setBackgroundColor(Color.WHITE)
            binding.tuesdayConstrain.setBackgroundColor(Color.WHITE)
            binding.wednesdayConstrain.setBackgroundColor(Color.WHITE)
            binding.thursdayConstrain.setBackgroundColor(Color.WHITE)
            binding.fridayConstrain.setBackgroundColor(Color.WHITE)
            binding.saturdayConstrain.setBackgroundColor(Color.WHITE)
            binding.sundayConstrain.setBackgroundColor(Color.WHITE)


            when (it){
                "Monday"->binding.mondayConstrain.setBackgroundColor(Color.GREEN)
                "Tuesday"->binding.tuesdayConstrain.setBackgroundColor(Color.GREEN)
                "Wednesday"->binding.wednesdayConstrain.setBackgroundColor(Color.GREEN)
                "Thursday"->binding.thursdayConstrain.setBackgroundColor(Color.GREEN)
                "Friday"->binding.fridayConstrain.setBackgroundColor(Color.GREEN)
                "Saturday"->binding.saturdayConstrain.setBackgroundColor(Color.GREEN)
                "Sunday"->binding.sundayConstrain.setBackgroundColor(Color.GREEN)
            }

        })

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