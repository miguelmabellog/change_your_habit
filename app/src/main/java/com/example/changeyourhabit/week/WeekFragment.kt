package com.example.changeyourhabit.week

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.changeyourhabit.R
import com.example.changeyourhabit.addNew.AddNewViewModel
import com.example.changeyourhabit.addNew.AddNewViewModelFactory
import com.example.changeyourhabit.database.PointDatabase
import com.example.changeyourhabit.databinding.AddNewFragmentBinding
import com.example.changeyourhabit.databinding.WeekFragmentBindingImpl

class WeekFragment : Fragment() {

    companion object {
        fun newInstance() = WeekFragment()
    }

    private lateinit var viewModel: WeekViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<WeekFragmentBindingImpl>(
            inflater, R.layout.week_fragment, container, false)
        val application = requireNotNull(this.activity).application
        val dataSource = PointDatabase.getInstance(application).pointDao
        val viewModelFactory = WeekViewModelFactory(dataSource, application)

        viewModel = ViewModelProvider(this,viewModelFactory).get(WeekViewModel::class.java)


        return binding.root
    }



}