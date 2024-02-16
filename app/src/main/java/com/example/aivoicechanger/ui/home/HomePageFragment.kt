package com.example.aivoicechanger.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.aivoicechanger.R
import com.example.aivoicechanger.databinding.FragmentHomePageBinding

class HomePageFragment : Fragment() {
    private lateinit var binding: FragmentHomePageBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomePageBinding.inflate(inflater, container, false)
        binding.imageViewSettings.setOnClickListener {
            findNavController().navigate(R.id.action_homePageFragment_to_settingsFragment)
        }
        return binding.root
    }
}