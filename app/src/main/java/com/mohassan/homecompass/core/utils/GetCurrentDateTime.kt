package com.mohassan.homecompass.core.utils

import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

object GetCurrentDateTime {
    fun getCurrentDateTime(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val currentDateTime = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SS'Z'")
            currentDateTime.format(formatter)
        } else {
            val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS'Z'", Locale.US)
            dateFormat.timeZone = TimeZone.getTimeZone("UTC")
            dateFormat.format(calendar.time)
        }
    }
}