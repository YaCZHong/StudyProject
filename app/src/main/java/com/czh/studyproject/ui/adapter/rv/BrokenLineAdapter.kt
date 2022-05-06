package com.czh.studyproject.ui.adapter.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.czh.studyproject.bean.Temp
import com.czh.studyproject.databinding.ItemBrokenLineBinding

class BrokenLineAdapter : RecyclerView.Adapter<BrokenLineAdapter.ViewHolder>() {

    private val data = mutableListOf<Temp>()

    inner class ViewHolder(private val binding: ItemBrokenLineBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.apply {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBrokenLineBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return data.size
    }
}