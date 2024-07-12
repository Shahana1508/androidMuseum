package com.example.practice.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.practice.R
import com.example.practice.base.BaseFragment
import com.example.practice.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val homeViewModel by viewModels<HomeViewModel>()
    private val adapter=HomeAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rv.adapter=adapter
        adapter.onclick={
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToRegionFragment(it))
        }
        observe()
    }
    fun observe(){
        homeViewModel.cities.observe(viewLifecycleOwner){
            adapter.updateList(it)
        }
    }
}