package com.example.aivoicechanger.ui.setting

import android.annotation.SuppressLint
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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aivoicechanger.R
import com.example.aivoicechanger.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding
    private lateinit var settingsAdapter: SettingsAdapter
    private lateinit var viewModel: SettingsViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        binding.apply {
            imageViewBackSettings.setOnClickListener {
                findNavController().navigate(R.id.action_settingsFragment_to_homePageFragment)
            }

            recyclerViewSettings.layoutManager = LinearLayoutManager(requireContext())
            // Fragmentin yaşam döngüsü kapsamında bir coroutine başlatılıyor.
            viewLifecycleOwner.lifecycleScope.launch {
                /*
                belirli bir yaşam döngüsü durumu boyunca çalışacak işlemleri belirlemek
                ve bu işlemlerin otomatik olarak fragment yaşam döngüsü durumu değiştiğinde durdurulmasıdır.

                Fragmentin yaşam döngüsü durumu CREATED olduğunda işlemi tekrarlamak için bir yapı başlatır.
                Bu, genellikle fragmentin oluşturulduğu anda ve yaşam döngüsü en az CREATED durumundayken
                çalışacak işlemleri içeren bir bloğu işaret eder.
                 */
                viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                    // Veri akışını toplamak için bir işlem başlatılıyor.
                    viewModel.settingsItem.collect { items ->
                        // Yeni veri öğeleriyle bir SettingsAdapter oluşturuluyor.
                        settingsAdapter = SettingsAdapter(items, requireContext())
                        recyclerViewSettings.adapter = settingsAdapter
                    }
                }
            }
        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : SettingsViewModel by viewModels()
        viewModel = tempViewModel
    }
}