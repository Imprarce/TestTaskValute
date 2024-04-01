package com.imprarce.android.testtaskvalute.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.imprarce.android.testtaskvalute.data.api.Fetch
import com.imprarce.android.testtaskvalute.data.model.MoneyItem

class MainValuteViewModel : ViewModel() {
    var moneyItemsLiveData : LiveData<List<MoneyItem>> = MutableLiveData()
    var dateItemsLiveData : LiveData<String> = MutableLiveData()

    init {
        moneyItemsLiveData = Fetch().fetchMoney()
        dateItemsLiveData = Fetch().fetchDate()
    }
}