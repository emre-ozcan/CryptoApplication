package com.emreozcan.cryptoapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.emreozcan.cryptoapp.databinding.RecyclerRowLayoutBinding
import com.emreozcan.cryptoapp.model.home.CryptoModel

/**
 * Created by @Emre Özcan on 19.04.2022
 */
class CryptoAdapter(private val listener: ItemClickListener) :
    PagingDataAdapter<CryptoModel, CryptoAdapter.CryptoViewHolder>(CRYPTO_MODEL_DIFF_UTIL) {

    companion object {
        private val CRYPTO_MODEL_DIFF_UTIL = object : DiffUtil.ItemCallback<CryptoModel>() {
            override fun areItemsTheSame(oldItem: CryptoModel, newItem: CryptoModel): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: CryptoModel, newItem: CryptoModel): Boolean =
                oldItem.id == newItem.id
        }
    }

    class CryptoViewHolder(private val binding: RecyclerRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: ItemClickListener, coin: CryptoModel) {
            binding.onItemClickListener = listener
            binding.coin = coin
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): CryptoViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecyclerRowLayoutBinding.inflate(layoutInflater, parent, false)
                return CryptoViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CryptoViewHolder.from(parent)

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        val currentItem = getItem(position)

        currentItem?.let {
            holder.bind(listener, it)
        }
    }
}