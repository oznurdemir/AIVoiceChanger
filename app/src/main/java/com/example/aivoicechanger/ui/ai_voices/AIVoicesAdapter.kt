package com.example.aivoicechanger.ui.ai_voices

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.aivoicechanger.R
import com.example.aivoicechanger.data.entity.generate_voice_ai.celebrity_info.CelebrityItem
import com.example.aivoicechanger.databinding.VoicesPageItemBinding
interface CelebrityClickedListener{
    fun onCelebrityClicked(celebrityImage : Int, celebrityName : Int, celebrityToken : Int)
}

class AIVoicesAdapter(private val celebrityItemsList : List<CelebrityItem>,
                      private val celebrityClickedListener: CelebrityClickedListener) : RecyclerView.Adapter<AIVoicesAdapter.ViewHolder>(){

    private var selectedPosition = RecyclerView.NO_POSITION

    inner class ViewHolder(val binding: VoicesPageItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = VoicesPageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return celebrityItemsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = celebrityItemsList[position]
        val h = holder.binding
        h.celebrity.setImageResource(item.image)
        h.textViewName.setText(item.name)

        // Öğe seçildiğinde veya seçimi kaldırıldığında arkaplan rengini güncelle
        if (position == selectedPosition) {
            h.celebrity.borderColor = ContextCompat.getColor(h.root.context, R.color.purple)
        } else {
            h.celebrity.borderColor = ContextCompat.getColor(h.root.context, R.color.light_grey)
        }

        h.root.setOnClickListener {
            // Önceki seçilen pozisyonu güncelle
            val previousSelectedPosition = selectedPosition
            // Yeni seçilen pozisyonu güncelle
            selectedPosition = holder.adapterPosition

            if (previousSelectedPosition != RecyclerView.NO_POSITION) {
                // Önceki seçilen öğenin görünümünü güncelle
                notifyItemChanged(previousSelectedPosition)
            }
            // Yeni seçilen öğenin görünümünü güncelle
            notifyItemChanged(selectedPosition)

            // Listener'a tıklanan öğeyi bildir
            celebrityClickedListener.onCelebrityClicked(item.image, item.name, item.token)
        }
    }
}