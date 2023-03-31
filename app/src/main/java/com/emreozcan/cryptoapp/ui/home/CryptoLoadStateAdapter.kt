package com.emreozcan.cryptoapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.emreozcan.cryptoapp.databinding.RecyclerRowFooterLayoutBinding

/**
 * Created by @Emre Özcan on 31.03.2023
 */
class CryptoLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<CryptoLoadStateAdapter.LoadStateViewHolder>() {

    inner class LoadStateViewHolder(private val binding: RecyclerRowFooterLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.footerRetryButton.setOnClickListener {
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState) {
            binding.apply {
                footerProgressBar.isVisible = loadState is LoadState.Loading
                footerRetryButton.isVisible = loadState !is LoadState.Loading
                footerTextView.isVisible = loadState !is LoadState.Loading
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding = RecyclerRowFooterLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return LoadStateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState = loadState)
    }
}