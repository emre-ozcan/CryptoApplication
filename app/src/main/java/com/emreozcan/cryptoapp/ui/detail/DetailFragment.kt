package com.emreozcan.cryptoapp.ui.detail

import android.os.Bundle
import android.transition.TransitionInflater
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.emreozcan.cryptoapp.base.BaseFragment
import com.emreozcan.cryptoapp.databinding.FragmentDetailBinding
import com.emreozcan.cryptoapp.model.detail.CoinDetail
import com.emreozcan.cryptoapp.model.detail.DetailResponse
import com.emreozcan.cryptoapp.utils.loadImage
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONArray
import org.json.JSONObject

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>(
    FragmentDetailBinding::inflate
) {
    override val viewModel by viewModels<DetailViewModel>()
    private val args by navArgs<DetailFragmentArgs>()

    override fun onCreateFinished() {
        initTransitions()
        initViews()
        args.coin.symbol?.let {
            viewModel.getDetail(it)
        }
    }

    private fun initViews() = with(binding) {
        ivDetail.loadImage("https://s2.coinmarketcap.com/static/img/coins/128x128/${args.coin.id}.png")
        tvDetailTitle.text = args.coin.name
        tvDetailSymbol.text = args.coin.symbol
    }

    private fun initTransitions() {
        val symbol = args.coin.symbol

        ViewCompat.setTransitionName(binding.ivDetail,"image${symbol}")
        ViewCompat.setTransitionName(binding.tvDetailTitle,"title${symbol}")
        ViewCompat.setTransitionName(binding.tvDetailSymbol,"symbol${symbol}")

        val animation = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation
    }

    override fun initializeListeners() {}

    override fun observeEvents() {
        with(viewModel) {
            detailResponse.observe(viewLifecycleOwner) {
                parseData(it)
            }
            isLoading.observe(viewLifecycleOwner) {
                handleView(it)
            }
            onError.observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun parseData(it: DetailResponse?) {
        val gson = Gson()
        val json = gson.toJson(it?.data)
        val jsonObject = JSONObject(json)
        val jsonArray = jsonObject[args.coin.symbol.toString()] as JSONArray

        val coin = gson.fromJson(jsonArray.getJSONObject(0).toString(), CoinDetail::class.java)

        coin?.let {
            with(binding) {
                tvDetailDescription.text = it.description
            }
        }
    }

    private fun handleView(isLoading: Boolean = false) {
        binding.tvDetailDescription.isVisible = !isLoading
        binding.pbDetail.isVisible = isLoading
    }
}