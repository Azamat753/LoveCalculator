package com.lawlett.lovecalculator.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.lawlett.lovecalculator.data.BoardModel
import com.lawlett.lovecalculator.databinding.ItemBoardBinding
import com.lawlett.lovecalculator.gone
import com.lawlett.lovecalculator.visible

class ViewPagerAdapter() :
    RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {
    var list: ArrayList<BoardModel> = arrayListOf()

    class ViewPagerViewHolder(private val binding: ItemBoardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(model: BoardModel) {
            binding.illustrationImg.load(model.screenShot)
            binding.boardContainer.setBackgroundResource(model.background)
            binding.title.text = model.title
            binding.description.text = model.description
            if (model.description.isEmpty()) {
                binding.description.gone()
            } else {
                binding.description.visible()
            }
        }
    }

    fun setData(list: ArrayList<BoardModel>) {
        this.list = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val binding = ItemBoardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewPagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

}