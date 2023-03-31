package com.emreozcan.cryptoapp.ui.home

import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.paging.LoadState
import com.emreozcan.cryptoapp.base.BaseFragment
import com.emreozcan.cryptoapp.databinding.FragmentHomeBinding
import com.emreozcan.cryptoapp.model.home.CryptoModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    FragmentHomeBinding::inflate
), ItemClickListener {

    override val viewModel by viewModels<HomeViewModel>()
    private lateinit var cryptoAdapter: CryptoAdapter

    override fun onCreateFinished() {
        initAdapter()
        initRecyclerView()
    }

    override fun initializeListeners() {
        with(binding) {
            retryButtonHomeFragment.setOnClickListener {
                cryptoAdapter.retry()
            }
        }
    }

    override fun observeEvents() {
        viewModel.cryptoResponse.observe(viewLifecycleOwner) {
            cryptoAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    private fun initAdapter() {
        cryptoAdapter = CryptoAdapter(this)
        cryptoAdapter.addLoadStateListener { loadState ->
            binding.apply {
                progressBarHomeFragment.isVisible = loadState.source.refresh is LoadState.Loading
                recyclerViewHomeFragment.isVisible = loadState.source.refresh is LoadState.NotLoading
                retryButtonHomeFragment.isVisible = loadState.source.refresh is LoadState.Error
                errorTextHomeFragment.isVisible = loadState.source.refresh is LoadState.Error

                if (loadState.source.refresh is LoadState.Error) {
                    errorTextHomeFragment.text =
                        (loadState.source.refresh as LoadState.Error).error.localizedMessage
                }
            }
        }
    }

    private fun initRecyclerView() {
        binding.recyclerViewHomeFragment.apply {
            setHasFixedSize(true)
            this.adapter =
                cryptoAdapter.withLoadStateFooter(footer = CryptoLoadStateAdapter { cryptoAdapter.retry() })
            postponeEnterTransition()
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
        }
    }

    override fun onItemClick(
        coin: CryptoModel, imageView: ImageView, titleTextView: TextView, symbolTextView: TextView
    ) {
        if (coin.symbol != null) {
            val extras = FragmentNavigatorExtras(
                imageView to "image${coin.symbol}",
                titleTextView to "title${coin.symbol}",
                symbolTextView to "symbol${coin.symbol}"
            )
            val navigation = HomeFragmentDirections.actionHomeFragmentToDetailFragment(coin)
            Navigation.findNavController(requireView()).navigate(navigation, extras)
        }
    }
}