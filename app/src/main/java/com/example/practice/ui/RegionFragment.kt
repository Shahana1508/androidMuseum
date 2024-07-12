package com.example.practice.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.practice.R
import com.example.practice.base.BaseFragment
import com.example.practice.databinding.FragmentMuseumBinding
import com.example.practice.databinding.FragmentRegionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegionFragment : BaseFragment<FragmentRegionBinding>(FragmentRegionBinding::inflate){
    private val args:RegionFragmentArgs by navArgs()
    private val viewModel by viewModels<RegionViewModel>()
    private val adapter=DistrictAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvdistrict.adapter=adapter
        viewModel.getDistrictsforcity(args.city)
        adapter.onclick={
            findNavController().navigate(RegionFragmentDirections.actionRegionFragmentToMuseumFragment( city = args.city,
                district = it))
        }
        observeData()
    }
    private fun observeData(){
        viewModel.districts.observe(viewLifecycleOwner){
            adapter.updateList(it)
        }
        viewModel.error.observe(viewLifecycleOwner){
            if(it){
                Toast.makeText(requireContext(),"error occured",Toast.LENGTH_SHORT).show()
            }
        }
    }
}