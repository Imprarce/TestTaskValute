package com.imprarce.android.testtaskvalute.presentation.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.imprarce.android.testtaskvalute.data.model.MoneyItem
import com.imprarce.android.testtaskvalute.databinding.ActivityValuteMainBinding
import com.imprarce.android.testtaskvalute.presentation.view.adapter.MoneyAdapter
import com.imprarce.android.testtaskvalute.presentation.viewmodel.MainValuteViewModel
import com.imprarce.android.testtaskvalute.utils.ApiResult
import com.imprarce.android.testtaskvalute.utils.CustomProgressBar.hideProgressBar
import com.imprarce.android.testtaskvalute.utils.CustomProgressBar.showProgressBar
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "MainValuteActivity"

@AndroidEntryPoint
class MainValuteActivity : AppCompatActivity() {
    private val valuteViewModel by viewModels<MainValuteViewModel>()
    private lateinit var binding: ActivityValuteMainBinding
    private lateinit var adapter : MoneyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityValuteMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        moneyDataObserver()

    }

    private fun moneyDataObserver(){
        valuteViewModel.moneyItemsLiveData.observe(this) { response ->
            when (response) {
                is ApiResult.Loading -> {
                    showProgressBar(this)
                    Log.d(TAG, "Loading")
                }
                is ApiResult.Success -> {
                    setAdapter(response.data)
                    binding.date.text = "Обновлено: ${response.date}"
                    hideProgressBar()
                    Log.d(TAG, "Success")
                }
                is ApiResult.Error -> {
                    hideProgressBar()
                    Log.e(TAG, "Error: ${response.message}")
                }
            }
        }
    }


    private fun setAdapter(moneyList : List<MoneyItem>){
        adapter = MoneyAdapter(moneyList)
        binding.recyclerViewMoney.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewMoney.adapter = adapter
    }

    override fun onPause() {
        super.onPause()
        valuteViewModel.stopRefreshing()
    }

    override fun onResume() {
        super.onResume()
        valuteViewModel.startRefreshing()
    }

}