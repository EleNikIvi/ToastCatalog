package com.sumup.challenge.toastcatalog.data.remote.base

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object DateTimeUtils {
    
    const val FORMAT_UI_TIMESTAMP = "d/MM/yy, h:mm a"
    fun String.getFormattedDateFromServerTimeStamp(): String {
        val localDateTime = ZonedDateTime.parse(this).toLocalDateTime()
        return localDateTime.format(
            DateTimeFormatter.ofPattern(FORMAT_UI_TIMESTAMP)
        )
    }
}