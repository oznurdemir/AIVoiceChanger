package com.example.aivoicechanger.ui.setting

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aivoicechanger.databinding.SettingsItemBinding
import com.example.aivoicechanger.utils.Constants

class SettingsAdapter(private val settingsItemsList : List<Int>, private val mContext : Context) : RecyclerView.Adapter<SettingsAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: SettingsItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = SettingsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return settingsItemsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = settingsItemsList[position]
        val h = holder.binding

        h.textViewItem.setText(item)

        h.root.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(Constants.SETTING_URL)
            mContext.startActivity(intent)
        }
    }

}