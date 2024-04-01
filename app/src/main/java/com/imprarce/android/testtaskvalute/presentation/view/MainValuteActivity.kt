package com.imprarce.android.testtaskvalute.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.imprarce.android.testtaskvalute.data.model.MoneyItem
import com.imprarce.android.testtaskvalute.databinding.ActivityValuteMainBinding
import com.imprarce.android.testtaskvalute.presentation.view.adapter.MoneyAdapter
import com.imprarce.android.testtaskvalute.presentation.viewmodel.MainValuteViewModel
import com.imprarce.android.testtaskvalute.utils.ApiResult
import com.imprarce.android.testtaskvalute.utils.CustomProgressBar.hideProgressBar
import com.imprarce.android.testtaskvalute.utils.CustomProgressBar.showProgressBar
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

private const val TAG = "MainValuteActivity"

class MainValuteActivity : AppCompatActivity() {
    private lateinit var valuteViewModel : MainValuteViewModel
    private lateinit var binding: ActivityValuteMainBinding
    private lateinit var adapter : MoneyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        valuteViewModel = ViewModelProvider(this).get(MainValuteViewModel::class.java)
        binding = ActivityValuteMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        valuteViewModel.getElements()
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
                    hideProgressBar()
                    setAdapter(response.data)
                    binding.date.text = "${binding.date.text} ${response.date}"
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
}