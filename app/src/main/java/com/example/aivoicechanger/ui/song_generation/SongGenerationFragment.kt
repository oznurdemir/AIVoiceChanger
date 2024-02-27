package com.example.aivoicechanger.ui.song_generation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.aivoicechanger.data.entity.generate_voice_ai.music_token.VoiceRequest
import com.example.aivoicechanger.data.entity.song_playing.SongPlayingData
import com.example.aivoicechanger.databinding.FragmentSongGenerationBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.UUID

@AndroidEntryPoint
class SongGenerationFragment : Fragment() {
    private lateinit var binding : FragmentSongGenerationBinding
    private lateinit var viewModel: SongGenerationViewModel
    private var image : Int = 0
    private var name : Int = 0
    var uuid = UUID.randomUUID()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSongGenerationBinding.inflate(inflater, container, false)
        val args :SongGenerationFragmentArgs by navArgs()
        name = args.celebrity.name
        image = args.celebrity.image
        val token = args.celebrity.token
        val text = args.celebrity.text

        val voice = VoiceRequest(text,requireContext().getString(token), uuid.toString())
        lifecycleScope.launch {
            viewModel.postVoice(voice)
        }
        collectEvents()
        return binding.root
    }

    private fun collectEvents() {
        lifecycleScope.launch {
            viewModel.voice.collect { voice ->
                voice?.inferenceJobToken?.let {
                    if (it.isNotEmpty()) {
                        Log.e("TOKEN", it)
                        viewModel.getMusicUrl(it)
                        getMusicUrl()
                    }
                }
            }
        }
    }

    private fun getMusicUrl() {
        lifecycleScope.launch {
            viewModel.music.collect{musicUrl->
                musicUrl?.let {
                    val path = musicUrl.maybePublicBucketWavAudioPath.toString()
                    Log.e("Music Url",path)
                    if(path.isNotEmpty()){
                        val musicInfo = SongPlayingData(image = image, name = name, musicPath = path)
                        val action = SongGenerationFragmentDirections.actionSongGenerationFragmentToSongPlayingFragment(musicInfo)
                        findNavController().navigate(action)
                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : SongGenerationViewModel by viewModels()
        viewModel = tempViewModel
    }
}