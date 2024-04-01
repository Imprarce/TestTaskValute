package com.imprarce.android.testtaskvalute.data.api.response

import com.google.gson.annotations.SerializedName
import com.imprarce.android.testtaskvalute.data.model.MoneyItem

data class MoneyResponse (
    @SerializedName("Valute")  val valute: Map<String, MoneyItem>,
    @SerializedName("Date")  val date: String
)