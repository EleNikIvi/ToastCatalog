package com.sumup.challenge.toastcatalog.domain

import com.sumup.challenge.toastcatalog.domain.model.ItemModel
import com.sumup.challenge.toastcatalog.domain.model.Result
import javax.inject.Inject

class ItemInteractor @Inject constructor(
    private val itemRepository: ItemRepository,
) {
    suspend fun getItems(): Result<List<ItemModel>> = itemRepository.getItems()
}