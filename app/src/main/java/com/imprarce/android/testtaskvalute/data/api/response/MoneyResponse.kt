package com.imprarce.android.testtaskvalute.data.api.response

import com.google.gson.annotations.SerializedName
import com.imprarce.android.testtaskvalute.data.model.MoneyItem

class MoneyResponse {
    @SerializedName("Valute") lateinit var valute: Map<String, MoneyItem>
    @SerializedName("Date") lateinit var date: String
}