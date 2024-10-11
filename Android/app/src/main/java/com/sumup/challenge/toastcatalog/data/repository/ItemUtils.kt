package com.sumup.challenge.toastcatalog.data.repository

import com.sumup.challenge.toastcatalog.data.remote.ItemDto
import com.sumup.challenge.toastcatalog.domain.model.ItemModel
import com.sumup.challenge.toastcatalog.data.remote.base.DateTimeUtils.getFormattedDateFromServerTimeStamp

object ItemUtils {
    fun ItemDto.toItemModel() =
        ItemModel(
            id = id,
            name = name,
            price = price,
            currency = currency,
            lastSold = lastSold.getFormattedDateFromServerTimeStamp()
        )
}