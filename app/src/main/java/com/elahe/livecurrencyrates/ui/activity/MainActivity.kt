package com.elahe.livecurrencyrates.ui.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.elahe.livecurrencyrates.R
import com.elahe.livecurrencyrates.util.BaseApiDataState
import com.elahe.livecurrencyrates.databinding.ActivityMainBinding
import com.elahe.livecurrencyrates.ui.adapter.CurrencyItemAdapter
import com.elahe.livecurrencyrates.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        init()
    }

    private fun init() {
        initList()
        fetchData()
        setUpdateDate()
    }

    @SuppressLint("SetTextI18n")
    private fun setUpdateDate() {
        lifecycleScope.launch {
            mViewModel.updateDate.collect {
                binding.tvUpdateDate.text = "Last updated: $it"
            }
        }
    }

    private fun initList() {
        binding.rvRates.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = CurrencyItemAdapter()
        }
    }

    private fun fetchData() {
        lifecycleScope.launch {
            mViewModel.response.collect {
                when (it) {
                    is BaseApiDataState.Loading -> {}

                    is BaseApiDataState.Success -> {
                        it.data.rates.let { newList ->
                            (binding.rvRates.adapter as CurrencyItemAdapter).updateRateListItems(
                                newList
                            )
                        }
                    }

                    is BaseApiDataState.Error -> {
                        Toast.makeText(this@MainActivity, it.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}