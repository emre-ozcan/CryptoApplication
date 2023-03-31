package com.emreozcan.cryptoapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by @Emre Özcan on 18.04.2022
 */
@HiltViewModel
class HomeViewModel @Inject constructor(repository: HomeRepository) : ViewModel() {

    val cryptoResponse = repository.getData().cachedIn(viewModelScope)
}