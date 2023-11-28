package com.elahe.livecurrencyrates.viewModel

import android.icu.util.Calendar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elahe.livecurrencyrates.util.BaseApiDataState
import com.elahe.livecurrencyrates.data.repository.RateRepo
import com.elahe.livecurrencyrates.data.model.ResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo: RateRepo) : ViewModel() {

    private var _response =
        MutableStateFlow<BaseApiDataState<ResponseModel>>(BaseApiDataState.Loading)
    val response: StateFlow<BaseApiDataState<ResponseModel>> = _response

    private val _updateDate = MutableStateFlow("")
    val updateDate: StateFlow<String> = _updateDate

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
                getTime()
                delay(120000)
            }
        }.start()
    }

    private fun getTime() {
        _updateDate.value = SimpleDateFormat(
            "dd/MM/yyyy - hh:mm",
            Locale.getDefault()
        ).format(Calendar.getInstance().time)
    }

    fun stopRefreshing() {
        job.cancel()
    }

    private fun refreshList() {
        viewModelScope.launch {
            repo.getRates().collect {
                _response.value = it
            }
        }
    }
}