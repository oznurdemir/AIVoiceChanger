package com.example.aivoicechanger.ui.ai_voices

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.aivoicechanger.R
import com.example.aivoicechanger.data.entity.generate_voice_ai.celebrity_info.CelebrityItem
import com.example.aivoicechanger.databinding.FragmentAIVoicesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AIVoicesFragment : Fragment() {
    private lateinit var binding: FragmentAIVoicesBinding
    private lateinit var viewModel: AIVoicesViewModel
    private lateinit var adapter: AIVoicesAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAIVoicesBinding.inflate(inflater, container, false)
        binding.apply {
            imageViewBackVoices.setOnClickListener {
                findNavController().navigate(R.id.action_AIVoicesFragment_to_homePageFragment)
            }
            recyclerViewCelebrity.layoutManager = StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL)
            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED){
                    viewModel.celebrityItems.collect{list->
                        adapter = AIVoicesAdapter(list, object : CelebrityClickedListener{
                            override fun onCelebrityClicked(
                                celebrityImage: Int,
                                celebrityName: Int,
                                celebrityToken: Int
                            ) {
                                val celebrity = CelebrityItem(celebrityImage, celebrityName, celebrityToken, binding.textViewContent.toString())
                                buttonGenerateVoice.setOnClickListener {
                                    val action = AIVoicesFragmentDirections.actionAIVoicesFragmentToSongGenerationFragment2(celebrity)
                                    findNavController().navigate(action)
                                }
                            }
                        })
                        recyclerViewCelebrity.adapter = adapter
                    }
                }
            }
        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : AIVoicesViewModel by viewModels()
        viewModel = tempViewModel
    }
}