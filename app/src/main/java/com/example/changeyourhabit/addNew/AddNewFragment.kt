package com.example.changeyourhabit.addNew

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.preference.PreferenceManager
import com.example.changeyourhabit.R
import com.example.changeyourhabit.database.PointDatabase
import com.example.changeyourhabit.database.PointDate
import com.example.changeyourhabit.databinding.AddNewFragmentBinding

class AddNewFragment : Fragment() {

    companion object {
        fun newInstance() = AddNewFragment()
    }

    private lateinit var viewModel: AddNewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<AddNewFragmentBinding>(
            inflater, R.layout.add_new_fragment, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = PointDatabase.getInstance(application).pointDao
        val viewModelFactory = AddNewViewModelFactory(dataSource, application)
        binding.lifecycleOwner=this
        viewModel = ViewModelProvider(this,viewModelFactory).get(AddNewViewModel::class.java)

        binding.viewModel=viewModel





        setHasOptionsMenu(true)
        return binding.root
    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.add_menu, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_calendar -> {
                findNavController().navigate(R.id.action_addNewFragment_to_weekFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }
}