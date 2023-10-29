package com.elahe.livecurrencyrates.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elahe.livecurrencyrates.core.base.BaseApiDataState
import com.elahe.livecurrencyrates.data.enum.Indicator
import com.elahe.livecurrencyrates.data.repository.RateRepo
import com.elahe.livecurrencyrates.data.model.RateModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo: RateRepo) : ViewModel() {

    private var _rateList = MutableStateFlow<List<RateModel>>(emptyList())
    val rateList: StateFlow<List<RateModel>> = _rateList

    private val job = Job()
    private val scope = CoroutineScope(job)

    init {
        refreshList()
        startRefreshing()
    }

    private fun startRefreshing() {
        scope.launch(start = CoroutineStart.LAZY) {
            while (true) {
                refreshList()
                delay(2000)
            }
        }.start()
    }

    fun stopRefreshing() {
        job.cancel()
    }

    private fun refreshList() {
        viewModelScope.launch {
            try {
                repo.getRates().collect {
                    when (it) {
                        is BaseApiDataState.Loading -> {}
                        is BaseApiDataState.Success -> {
                            it.data.rates.let { newList ->
                                if (_rateList.value.isNotEmpty()) {
                                    for (item in newList) {
                                        val oldItem = _rateList.value.find { it.symbol == item.symbol }
                                        if (oldItem != null) {
                                            if (item.price >= oldItem.price)
                                                item.priceColor = Indicator.GREEN
                                            else
                                                item.priceColor = Indicator.RED
                                        }
                                    }
                                }
                                _rateList.value = newList
                            }
                        }

                        is BaseApiDataState.Error -> {}
                        else -> {}
                    }
                }
            } catch (e: Exception) {
                // handle error
            }
        }
    }
}