package com.example.aivoicechanger.ui.song_playing

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.example.aivoicechanger.R
import com.example.aivoicechanger.databinding.FragmentSongPlayingBinding
import com.example.aivoicechanger.utils.Constants

class SongPlayingFragment : Fragment() {
    private lateinit var binding: FragmentSongPlayingBinding
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var seekBar: SeekBar
    private lateinit var textViewTimeStart: TextView
    private lateinit var textViewTimeEnd: TextView
    private lateinit var imageViewPlay: ImageView
    private lateinit var imageViewBack: ImageView
    private lateinit var imageViewFoward: ImageView
    private val handler = Handler(Looper.getMainLooper())
    private var isPlaying = false
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSongPlayingBinding.inflate(inflater, container, false)
        val args : SongPlayingFragmentArgs by navArgs()
        val image = args.info.image
        val name = args.info.name
        val path = args.info.musicPath
        val audioUrl = Constants.SONG_BASE_PATH + path

        Log.e("AUDIO_URL", audioUrl)

        mediaPlayer = MediaPlayer()
        mediaPlayer.setDataSource(audioUrl)
        mediaPlayer.prepare()

        binding.apply {
            celebrity.setImageResource(image)
            textViewCelebrityName.setText(name)
            this@SongPlayingFragment.seekBar = seekBar
            this@SongPlayingFragment.textViewTimeStart = textViewTimeStart
            this@SongPlayingFragment.textViewTimeEnd = textViewTimeEnd
            this@SongPlayingFragment.imageViewPlay = imageViewPlayArrow
            imageViewBack = imageViewReplace
            imageViewFoward = imageViewBefore
        }

        initializeSeekBar()

        imageViewPlay.setOnClickListener {
            if (isPlaying) {
                pauseMusic()
            } else {
                playMusic()
            }
            mediaPlayer.setOnCompletionListener {
                isPlaying = false
                imageViewPlay.setImageResource(R.drawable.ic_play_arrow)
            }
        }

        imageViewBack.setOnClickListener {
            rewindMusic()
        }

        imageViewFoward.setOnClickListener {
            forwardMusic()
        }
        return binding.root
    }

    private fun initializeSeekBar() {
        seekBar.max = mediaPlayer.duration

        mediaPlayer.setOnPreparedListener {
            val duration = mediaPlayer.duration
            val minutes = duration / 1000 / 60
            val seconds = duration / 1000 % 60
            textViewTimeEnd.text = String.format("%02d:%02d", minutes, seconds)
            updateSeekBar()
        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    private fun updateSeekBar() {
        seekBar.progress = mediaPlayer.currentPosition

        val minutes = mediaPlayer.currentPosition / 1000 / 60
        val seconds = mediaPlayer.currentPosition / 1000 % 60
        textViewTimeStart.text = String.format("%02d:%02d", minutes, seconds)

        if (isPlaying) {
            handler.postDelayed({ updateSeekBar() }, 1000)
        }
    }

    private fun playMusic() {
        mediaPlayer.start()
        isPlaying = true
        imageViewPlay.setImageResource(R.drawable.ic_pause)
        updateSeekBar()
    }

    private fun pauseMusic() {
        mediaPlayer.pause()
        isPlaying = false
        imageViewPlay.setImageResource(R.drawable.ic_play_arrow)
    }

    private fun rewindMusic() {
        val currentPosition = mediaPlayer.currentPosition
        val newPosition = if (currentPosition - 15000 > 0) currentPosition - 15000 else 0
        mediaPlayer.seekTo(newPosition)
    }

    private fun forwardMusic() {
        val currentPosition = mediaPlayer.currentPosition
        val newPosition = if (currentPosition + 15000 < mediaPlayer.duration) currentPosition + 15000 else mediaPlayer.duration
        mediaPlayer.seekTo(newPosition)
    }
}