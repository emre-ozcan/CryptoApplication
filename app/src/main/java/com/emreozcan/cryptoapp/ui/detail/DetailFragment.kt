package com.emreozcan.cryptoapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.emreozcan.cryptoapp.R
import com.emreozcan.cryptoapp.base.BaseFragment
import com.emreozcan.cryptoapp.databinding.FragmentDetailBinding
import com.emreozcan.cryptoapp.model.detail.CoinDetail
import com.emreozcan.cryptoapp.model.detail.DetailResponse
import com.emreozcan.cryptoapp.utils.Constants.API_KEY
import com.emreozcan.cryptoapp.utils.loadImage
import com.google.gson.Gson
import com.google.gson.JsonObject
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONArray
import org.json.JSONObject

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding,DetailViewModel>(
    FragmentDetailBinding::inflate
) {
    override val viewModel by viewModels<DetailViewModel>()
    private val args by navArgs<DetailFragmentArgs>()

    override fun onCreateFinished() {
        viewModel.getDetail(API_KEY,args.symbol)

    }

    override fun initializeListeners() {
    }

    override fun observeEvents() {
        with(viewModel){
            detailResponse.observe(viewLifecycleOwner, Observer {
                parseData(it)
            })
            isLoading.observe(viewLifecycleOwner, Observer {
                handleView(it)
            })
            onError.observe(viewLifecycleOwner, Observer {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            })
        }
    }

    private fun parseData(it: DetailResponse?) {
        val gson = Gson()
        val json = gson.toJson(it?.data)
        val jsonObject = JSONObject(json)
        val jsonArray = jsonObject[args.symbol] as JSONArray

        val coin = gson.fromJson(jsonArray.getJSONObject(0).toString(), CoinDetail::class.java)

        coin?.let {
            with(binding){
                ivDetail.loadImage(it.logo)
                tvDetailTitle.text = it.name
                tvDetailSymbol.text = it.symbol
                tvDetailDescription.text = it.description
            }
        }
    }

    private fun handleView(isLoading: Boolean = false){
        binding.detailGroup.isVisible = !isLoading
        binding.pbDetail.isVisible = isLoading
    }
}