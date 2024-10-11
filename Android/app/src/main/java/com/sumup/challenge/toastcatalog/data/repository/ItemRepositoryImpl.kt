package com.sumup.challenge.toastcatalog.data.repository

import com.sumup.challenge.toastcatalog.data.remote.ItemApi
import com.sumup.challenge.toastcatalog.data.remote.base.NetworkResponse
import com.sumup.challenge.toastcatalog.data.repository.ItemUtils.toItemModel
import com.sumup.challenge.toastcatalog.domain.model.ItemModel
import com.sumup.challenge.toastcatalog.domain.ItemRepository
import com.sumup.challenge.toastcatalog.domain.model.Result
import timber.log.Timber
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
    private val itemApi: ItemApi,
) : ItemRepository {
    override suspend fun getItems(): Result<List<ItemModel>> {
        return try {
            when (val items = itemApi.getItems()) {
                is NetworkResponse.Success -> {
                    Result.Success(items.body.map { it.toItemModel() })
                }
                is NetworkResponse.Error<*> -> {
                    Result.error(items)
                }
            }
        } catch (e: Exception) {
            Timber.d("ItemRepositoryImpl Exception e $e")
            Result.error()
        }
    }
}