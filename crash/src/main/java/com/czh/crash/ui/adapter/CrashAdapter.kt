package com.czh.crash.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.czh.crash.R
import com.czh.crash.db.Crash
import com.czh.crash.ui.util.ParseTimeUtil

internal class CrashAdapter(private val data: List<Crash>, private val onClick: (Crash) -> Unit) :
    RecyclerView.Adapter<CrashAdapter.ViewHolder>() {

    class ViewHolder(itemView: View, private val onClick: (Crash) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val tvCrash = itemView.findViewById<TextView>(R.id.tv_crash)
        private val tvTime = itemView.findViewById<TextView>(R.id.tv_crash_time)
        private var current: Crash? = null

        init {
            itemView.setOnClickListener {
                current?.let {
                    onClick.invoke(it)
                }
            }
        }

        fun bind(crash: Crash) {
            this.current = crash
            tvCrash.text = crash.throwableSimple
            tvTime.text = ParseTimeUtil.parseTime(crash.timestamp)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_crash, parent, false)
        return ViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}