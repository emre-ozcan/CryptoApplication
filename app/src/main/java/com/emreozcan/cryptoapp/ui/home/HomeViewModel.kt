package com.emreozcan.cryptoapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emreozcan.cryptoapp.model.home.CryptoResponse
import com.emreozcan.cryptoapp.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by @Emre Özcan on 18.04.2022
 */
@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {

    val cryptoResponse: MutableLiveData<CryptoResponse?> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData(true)
    val onError: MutableLiveData<String?> = MutableLiveData()

    fun getData(
        limit: String
    ) = viewModelScope.launch {
        isLoading.value = true
        when (val request = repository.getData(limit)) {
            is NetworkResult.Success -> {
                cryptoResponse.value = request.data
                isLoading.value = false
            }
            is NetworkResult.Error -> {
                onError.value = request.message
                isLoading.value = false
            }
        }
    }
}