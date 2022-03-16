package com.lawlett.lovecalculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.lawlett.lovecalculator.data.BoardModel
import com.lawlett.lovecalculator.databinding.ItemBoardBinding

class ViewPagerAdapter(private val pagerListener: PagerListener) :
    RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {
    var list: ArrayList<BoardModel> = arrayListOf()

    class ViewPagerViewHolder(val binding: ItemBoardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(model: BoardModel) {
            binding.illustrationImg.load(model.screenShot)
            binding.boardContainer.setBackgroundResource(model.background)

            binding.title.text = model.title
            binding.description.text = model.description
            if (model.description.isEmpty()) {
                binding.description.gone()
                binding.startBtn.visible()
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
        holder.binding.startBtn.setOnClickListener { pagerListener.onStartClick() }
    }

    override fun getItemCount(): Int = list.size

}