package com.example.aivoicechanger.ui.song_generation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aivoicechanger.databinding.FragmentSongGenerationBinding

class SongGenerationFragment : Fragment() {
    private lateinit var binding : FragmentSongGenerationBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSongGenerationBinding.inflate(inflater, container, false)
        return binding.root
    }
}