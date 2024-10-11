package com.sumup.challenge.toastcatalog.data.repository

import com.sumup.challenge.toastcatalog.TestUtils
import com.sumup.challenge.toastcatalog.MainDispatcherExtension
import com.sumup.challenge.toastcatalog.data.remote.ItemApi
import com.sumup.challenge.toastcatalog.data.remote.ItemDto
import com.sumup.challenge.toastcatalog.data.remote.base.NetworkResponse
import com.sumup.challenge.toastcatalog.domain.model.Result
import com.sumup.challenge.toastcatalog.domain.model.ItemModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MainDispatcherExtension::class)
class ItemRepositoryImplTest {
    private val expectedResponseData = listOf(
        ItemDto(
            name = "Avocado Toast",
            price = "5.99",
            id = 1,
            currency = "EUR",
            lastSold = "2020-11-28T15:14:22Z"
        ),
        ItemDto(
            name = "Bacon Toast",
            price = "1.99",
            id = 2,
            currency = "EUR",
            lastSold = "2020-11-28T15:14:22Z"
        ),
        ItemDto(
            name = "Crunchy Toast",
            price = "0.99",
            id = 3,
            currency = "EUR",
            lastSold = "2020-11-28T15:14:22Z"
        ),
    )
    private val expectedData = expectedResponseData.map {
        ItemModel(
            id = it.id,
            name = it.name,
            price = it.price,
            currency = it.currency,
            lastSold = "28/11/20, 3:14 PM"
        )
    }
    private var isNetworkResponseSuccessful = false

    private val itemApi = mockk<ItemApi> {
        coEvery {
            getItems()
        } coAnswers {
            if (isNetworkResponseSuccessful) {
                NetworkResponse.Success(expectedResponseData)
            } else {
                NetworkResponse.Error(TestUtils.TEST_NETWORK_ERROR, 0)
            }
        }
    }
    private val repository = ItemRepositoryImpl(itemApi)

    @Test
    fun `getItems should call correct method from remote and return expectedData`() = runTest {
        isNetworkResponseSuccessful = true
        val result = repository.getItems()

        coVerify {
            itemApi.getItems()
        }

        assert(result is Result.Success)
        assert((result as Result.Success).data == expectedData)
    }

    @Test
    fun `getItems should return error when request fails`() = runTest {
        isNetworkResponseSuccessful = false
        val result = repository.getItems()

        assert(result is Result.Error)
    }
}