package com.example.changeyourhabit.addNew

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.changeyourhabit.R

class AddNewFragment : Fragment() {

    companion object {
        fun newInstance() = AddNewFragment()
    }

    private lateinit var viewModel: AddNewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_new_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddNewViewModel::class.java)
        // TODO: Use the ViewModel
    }

}