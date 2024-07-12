package com.example.practice.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.practice.base.BaseFragment
import com.example.practice.databinding.FragmentMuseumBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MuseumFragment : BaseFragment<FragmentMuseumBinding>(FragmentMuseumBinding::inflate){
    private val args:MuseumFragmentArgs by navArgs()
    private val museumViewModel by viewModels<MuseumViewModel>()
    private val museumadapter=MuseumAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.museumrv.adapter=museumadapter
        museumViewModel.getMuseums(args.city,args.district)
        observedata()
    }
    fun observedata(){
        museumViewModel.museums.observe(viewLifecycleOwner){
            museumadapter.updateList(it)
        }
    }
}