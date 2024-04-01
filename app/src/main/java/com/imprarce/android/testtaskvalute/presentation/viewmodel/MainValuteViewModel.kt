package com.imprarce.android.testtaskvalute.presentation.viewmodel

import androidx.lifecycle.*
import com.imprarce.android.testtaskvalute.data.api.Fetch
import com.imprarce.android.testtaskvalute.data.model.MoneyItem
import com.imprarce.android.testtaskvalute.utils.ApiResult
import kotlinx.coroutines.launch

class MainValuteViewModel : ViewModel() {

    private val fetch = Fetch()

    private val _moneyItemsLiveData: MutableLiveData<ApiResult<List<MoneyItem>>> = MutableLiveData()
    val moneyItemsLiveData: LiveData<ApiResult<List<MoneyItem>>> = _moneyItemsLiveData
    fun getElements() {
        viewModelScope.launch {
            _moneyItemsLiveData.value = ApiResult.Loading


            fetch.fetchMoney().observeForever { response ->
                when (response) {
                    is ApiResult.Success -> {
                        _moneyItemsLiveData.value = ApiResult.Success(data = response.data, date = response.date)
                    }
                    is ApiResult.Error -> {
                        _moneyItemsLiveData.value = response
                    }
                    else -> {}
                }

            }
        }
    }
}