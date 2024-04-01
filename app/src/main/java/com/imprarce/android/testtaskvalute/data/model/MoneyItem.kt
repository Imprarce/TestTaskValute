package com.imprarce.android.testtaskvalute.data.model

import com.google.gson.annotations.SerializedName

data class MoneyItem (
    @SerializedName("ID") var id : String = "",
    @SerializedName("NumCode") var numCode : String = "",
    @SerializedName("CharCode") var charCode : String = "",
    @SerializedName("Nominal") var nominal : String = "",
    @SerializedName("Name") var name : String = "",
    @SerializedName("Value") var value : String = "",
    @SerializedName("Previous") var previous : String = ""
)