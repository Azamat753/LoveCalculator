package com.lawlett.lovecalculator.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lawlett.lovecalculator.data.LoveModel
import com.lawlett.lovecalculator.databinding.ItemHistoryBinding

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
    private var list: List<LoveModel> = arrayListOf()

    inner class HistoryViewHolder(private val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(model: LoveModel) {
            val present = model.present+ "%"
            binding.itemTxtFirstName.text = model.female
            binding.itemTxtSecondName.text = model.male
            binding.itemTxtPresent.text = present
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = ItemHistoryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun setData(list: List<LoveModel>) {
        this.list = list
        notifyDataSetChanged()
    }
}