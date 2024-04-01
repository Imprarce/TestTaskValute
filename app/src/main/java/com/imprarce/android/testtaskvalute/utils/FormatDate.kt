package com.imprarce.android.testtaskvalute.utils

import java.text.SimpleDateFormat
import java.util.*

class FormatDate {
    companion object {
        fun formatDate(inputDate: String): String {
            return try {
                val parsedDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.getDefault()).parse(inputDate)
                SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault()).format(parsedDate)
            } catch (e: Exception) {
                ""
            }
        }
    }
}