package com.imprarce.android.testtaskvalute.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imprarce.android.testtaskvalute.data.api.response.MoneyResponse
import com.imprarce.android.testtaskvalute.domain.usecase.MoneyUseCase
import com.imprarce.android.testtaskvalute.utils.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainValuteViewModel @Inject constructor(
    private val moneyUseCase: MoneyUseCase
) : ViewModel() {

    private val _moneyItemsLiveData: MutableLiveData<ApiResult<MoneyResponse>> = MutableLiveData()
    val moneyItemsLiveData: LiveData<ApiResult<MoneyResponse>> = _moneyItemsLiveData

    private var refreshingJob: Job? = null
    private val refreshIntervalMillis: Long = 30000

    fun startRefreshing() {
        refreshingJob = viewModelScope.launch {
            while (true) {
                getElements()
                delay(refreshIntervalMillis)
            }
        }
    }

    fun stopRefreshing() {
        refreshingJob?.cancel()
    }
    fun getElements() {
        viewModelScope.launch {
            _moneyItemsLiveData.value = ApiResult.Loading

            try {
                moneyUseCase.callMoneyApi()
                    .collect{
                        result -> _moneyItemsLiveData.value = result
                    }
            } catch (e: Exception) {
                _moneyItemsLiveData.value = ApiResult.Error("Failed to fetch money: ${e.message}")
            }
        }
    }

    override fun onCleared() {
        stopRefreshing()
        super.onCleared()
    }
}

