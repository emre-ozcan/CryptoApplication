package com.emreozcan.cryptoapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.emreozcan.cryptoapp.databinding.RecyclerRowLayoutBinding
import com.emreozcan.cryptoapp.model.home.Data

/**
 * Created by @Emre Özcan on 19.04.2022
 */
class HomeRecyclerAdapter(private val listener: ItemClickListener): RecyclerView.Adapter<HomeRecyclerAdapter.MViewHolder>() {

    private var coins = emptyList<Data>()

    class MViewHolder(private val binding: RecyclerRowLayoutBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(listener: ItemClickListener,coin: Data){
            binding.onItemClickListener = listener
            binding.coin = coin
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup) :MViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecyclerRowLayoutBinding.inflate(layoutInflater,parent,false)
                return MViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MViewHolder.from(parent)

    override fun onBindViewHolder(holder: MViewHolder, position: Int)  = holder.bind(listener, coins[position])

    override fun getItemCount() = coins.size

    fun setList(newList: List<Data>){
        coins = newList
        notifyDataSetChanged()
    }
}