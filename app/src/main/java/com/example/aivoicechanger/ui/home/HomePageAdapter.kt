package com.example.aivoicechanger.ui.home

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.aivoicechanger.R
import com.example.aivoicechanger.data.entity.song.Song
import com.example.aivoicechanger.databinding.HomePaceItemBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

interface VoiceClickedListener {
    fun onVoiceClicked(currentItem : Song?)
}

class HomePageAdapter (var mContext: Context, private val viewModel: HomePageViewModel, private val voiceClickedListener: VoiceClickedListener) :  RecyclerView.Adapter<HomePageAdapter.ViewHolder>(){
    class ViewHolder(val binding: HomePaceItemBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffUtilCallback = object : DiffUtil.ItemCallback<Song>() {
        override fun areItemsTheSame(
            oldItem: Song,
            newItem: Song
        ): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(
            oldItem: Song,
            newItem: Song
        ): Boolean {
            return oldItem == newItem
        }
    }

    var differ = AsyncListDiffer(this,diffUtilCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            HomePaceItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = differ.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = differ.currentList[position]
        holder.binding.apply {
            celebrityImage.setImageResource(currentItem.celebrityImage)
            textViewGeneration.text = "00${currentItem.id} Generation"
            textViewCelebrity.setText(currentItem.celebrityName)
            textViewInput.text = currentItem.text
        }
        holder.binding.root.setOnClickListener {
            voiceClickedListener.onVoiceClicked(currentItem)
        }
        holder.binding.root.setOnLongClickListener {
            val dialog = BottomSheetDialog(mContext)
            dialog.setContentView(R.layout.sample_dialog)

            val txtDelete = dialog.findViewById<TextView>(R.id.deleteTxt)
            val txtShare = dialog.findViewById<TextView>(R.id.shareTxt)

            txtDelete?.setOnClickListener {
                dialog.dismiss()
                val voiceToDelete = differ.currentList[position]
                val alertDialog = AlertDialog.Builder(mContext).create()
                alertDialog.setTitle("Delete Voice")
                alertDialog.setMessage("Are you sure want to delete this voice?")
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Delete") { _, _ ->
                    viewModel.deleteVoice(voiceToDelete)
                    alertDialog.dismiss()
                }
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel") { _,_ ->
                    alertDialog.dismiss()
                }
                alertDialog.show()
            }

            txtShare?.setOnClickListener {
                dialog.dismiss()
                val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "audio/*"
                putExtra(Intent.EXTRA_STREAM, Uri.parse(currentItem.url))
                putExtra(Intent.EXTRA_TITLE, "Introducing content previews")
                flags = Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION
                }
                val chooser = Intent.createChooser(shareIntent, null)
                mContext.startActivity(chooser)
            }
            dialog.show()
            true
        }
    }
}