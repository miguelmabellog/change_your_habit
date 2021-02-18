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

            view?.resources?.getColor(R.color.constrain)?.let { it1 ->
                binding.mondayConstrain.setBackgroundColor(
                    it1
                )
            }
            view?.resources?.getColor(R.color.constrain)?.let { it1 ->
                binding.tuesdayConstrain.setBackgroundColor(
                    it1
                )
            }
            view?.resources?.getColor(R.color.constrain)?.let { it1 ->
                binding.wednesdayConstrain.setBackgroundColor(
                    it1
                )
            }
            view?.resources?.getColor(R.color.constrain)?.let { it1 ->
                binding.thursdayConstrain.setBackgroundColor(
                    it1
                )
            }
            view?.resources?.getColor(R.color.constrain)?.let { it1 ->
                binding.fridayConstrain.setBackgroundColor(
                    it1
                )
            }
            view?.resources?.getColor(R.color.constrain)?.let { it1 ->
                binding.saturdayConstrain.setBackgroundColor(
                    it1
                )
            }
            view?.resources?.getColor(R.color.constrain)?.let { it1 ->
                binding.sundayConstrain.setBackgroundColor(
                    it1
                )
            }



            when (it){
                "Monday"-> view?.resources?.getColor(R.color.hilight)?.let { it1 ->
                    binding.mondayConstrain.setBackgroundColor(
                        it1
                    )
                }
                "Tuesday"-> view?.resources?.getColor(R.color.hilight)?.let { it1 ->
                    binding.tuesdayConstrain.setBackgroundColor(
                        it1
                    )
                }
                "Wednesday"-> view?.resources?.getColor(R.color.hilight)?.let { it1 ->
                    binding.wednesdayConstrain.setBackgroundColor(
                        it1
                    )
                }
                "Thursday"-> view?.resources?.getColor(R.color.hilight)?.let { it1 ->
                    binding.thursdayConstrain.setBackgroundColor(
                        it1
                    )
                }
                "Friday"-> view?.resources?.getColor(R.color.hilight)?.let { it1 ->
                    binding.fridayConstrain.setBackgroundColor(
                        it1
                    )
                }
                "Saturday"-> view?.resources?.getColor(R.color.hilight)?.let { it1 ->
                    binding.saturdayConstrain.setBackgroundColor(
                        it1
                    )
                }
                "Sunday"-> view?.resources?.getColor(R.color.hilight)?.let { it1 ->
                    binding.sundayConstrain.setBackgroundColor(
                        it1
                    )
                }
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