package com.emreozcan.cryptoapp.ui.home

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.emreozcan.cryptoapp.base.BaseFragment
import com.emreozcan.cryptoapp.databinding.FragmentHomeBinding
import com.emreozcan.cryptoapp.model.home.Data
import com.emreozcan.cryptoapp.utils.Constants.LIMIT
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    FragmentHomeBinding::inflate
) {
    override val viewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getData(LIMIT)
    }

    override fun onCreateFinished() {
    }

    override fun initializeListeners() {

    }

    override fun observeEvents() {
        with(viewModel) {
            cryptoResponse.observe(viewLifecycleOwner, Observer {
                it?.let {
                    it.data?.let { it1 -> setRecycler(it1) }
                }
            })
            isLoading.observe(viewLifecycleOwner, Observer {
                handleViews(it)
            })

            onError.observe(viewLifecycleOwner, Observer {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            })
        }
    }

    private fun setRecycler(data: List<Data>) {
        val mAdapter = HomeRecyclerAdapter(object : ItemClickListener {
            override fun onItemClick(
                coin: Data,
                imageView: ImageView,
                titleTextView: TextView,
                symbolTextView: TextView
            ) {
                if (coin.symbol != null) {
                    val extras = FragmentNavigatorExtras(
                        imageView to "image${coin.symbol}",
                        titleTextView to "title${coin.symbol}",
                        symbolTextView to "symbol${coin.symbol}"
                    )
                    val navigation =
                        HomeFragmentDirections.actionHomeFragmentToDetailFragment(coin)
                    Navigation.findNavController(requireView()).navigate(navigation, extras)
                }
            }
        })

        binding.rvHome.apply {
            this.adapter = mAdapter
            postponeEnterTransition()
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
        }
        mAdapter.setList(data)
    }

    private fun handleViews(isLoading: Boolean = false) {
        binding.rvHome.isVisible = !isLoading
        binding.pbHome.isVisible = isLoading
    }
}