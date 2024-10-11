package com.sumup.challenge.toastcatalog.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sumup.challenge.toastcatalog.domain.ItemInteractor
import com.sumup.challenge.toastcatalog.domain.model.ItemModel
import com.sumup.challenge.toastcatalog.domain.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(
    private val itemInteractor: ItemInteractor,
) : ViewModel() {

    private val _items = MutableStateFlow<List<ItemModel>>(emptyList())
    val items = _items.asStateFlow()

    init {
        loadItems()
    }

    private fun loadItems() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = itemInteractor.getItems()) {
                is Result.Success -> {
                    _items.update {
                        result.data
                    }
                }

                is Result.Error -> {
                    // TODO Handle error
                }
            }
        }
    }
}