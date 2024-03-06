package com.example.aivoicechanger.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aivoicechanger.R
import com.example.aivoicechanger.data.entity.song.Song
import com.example.aivoicechanger.data.entity.song_playing.SongPlayingData
import com.example.aivoicechanger.databinding.FragmentHomePageBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomePageFragment : Fragment() {
    private lateinit var binding: FragmentHomePageBinding
    private val viewModel : HomePageViewModel by viewModels()
    private lateinit var voiceAdapter :HomePageAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomePageBinding.inflate(inflater, container, false)
        binding.imageViewSettings.setOnClickListener {
            findNavController().navigate(R.id.action_homePageFragment_to_settingsFragment)
        }
        binding.buttonGenerate.setOnClickListener {
            findNavController().navigate(R.id.action_homePageFragment_to_AIVoicesFragment)
        }
        binding.imageViewAdd.setOnClickListener {
            findNavController().navigate(R.id.action_homePageFragment_to_AIVoicesFragment)
        }

        setUpRv()
        getVoiceList()

        return binding.root
    }

    private fun setUpRv() {
        voiceAdapter = HomePageAdapter(requireContext(), viewModel, object : VoiceClickedListener{
            override fun onVoiceClicked(currentItem: Song?) {
                currentItem?.let {
                    val voice = SongPlayingData(currentItem.celebrityImage, currentItem.celebrityName, currentItem.url, currentItem.text)
                    val action = HomePageFragmentDirections.actionHomePageFragmentToSongPlayingFragment(voice)
                    findNavController().navigate(action)
                }
            }

        })
        binding.recyclerViewVoices.apply {
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            adapter = voiceAdapter
            setHasFixedSize(true)
        }
    }
    private fun getVoiceList() {
        viewModel.getVoice().observe(viewLifecycleOwner){
            voiceAdapter.differ.submitList(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.viewModelScope.launch {
            viewModel.voiceCount.collect{
                if(it > 0){
                    binding.recyclerViewVoices.visibility = View.VISIBLE
                    binding.imageViewAdd.visibility = View.VISIBLE
                    binding.buttonGenerate.visibility = View.INVISIBLE
                    binding.imageView.visibility = View.INVISIBLE
                    binding.textViewGenerateA.visibility = View.INVISIBLE
                    binding.textViewStart.visibility = View.INVISIBLE
                }
            }
        }
    }
}