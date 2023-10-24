package com.elahe.livecurrencyrates.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elahe.livecurrencyrates.core.base.BaseApiDataState
import com.elahe.livecurrencyrates.data.RateRepo
import com.elahe.livecurrencyrates.data.model.RateModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo: RateRepo) : ViewModel() {

    private var _rateList = MutableStateFlow<List<RateModel>>(emptyList())
    val rateList: StateFlow<List<RateModel>> = _rateList

    init {
        refreshList()
    }

    private fun refreshList() {
        viewModelScope.launch {
            try {
                val response = repo.getRates().collect {
                    when (it) {
                        is BaseApiDataState.Loading -> {}
                        is BaseApiDataState.Success -> {
                            it.data.rates.let { list ->
                                _rateList.value = list
                            }
                        }

                        is BaseApiDataState.Error -> {}
                    }
                }
            } catch (e: Exception) {
                // handle error
            }
        }
    }
}