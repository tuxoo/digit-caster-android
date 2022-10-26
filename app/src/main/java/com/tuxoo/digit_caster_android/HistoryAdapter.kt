package com.tuxoo.digit_caster_android

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tuxoo.digit_caster_android.databinding.ItemHistoryBinding
import com.tuxoo.digit_caster_android.model.calculation.entity.HistoryItem

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    var history: List<HistoryItem> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = history.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HistoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoryBinding.inflate(inflater, parent, false)

        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val historyItem = history[position]

        with(holder.binding) {
            itemOperation.text = historyItem.operation
        }
    }

    class HistoryViewHolder(
        val binding: ItemHistoryBinding
    ) : RecyclerView.ViewHolder(binding.root)
}