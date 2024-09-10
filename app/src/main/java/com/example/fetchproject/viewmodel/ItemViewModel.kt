package com.example.fetchproject.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchproject.data.model.Item
import com.example.fetchproject.data.network.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ItemViewModel : ViewModel() {

    private val _items = MutableStateFlow<Map<Int, List<Item>>>(emptyMap())
    val items: StateFlow<Map<Int, List<Item>>> = _items

    init {
        fetchItems()
    }

    fun fetchItems() {
        viewModelScope.launch {
            try {
                val fetchedItems = ApiService.fetchItems()
                val filteredAndGroupedItems = fetchedItems
                    .filter { !it.name.isNullOrBlank() } // Filter out blank or null names
                    .groupBy { it.listId } // Group by listId

                _items.value = filteredAndGroupedItems
            } catch (e: Exception) {
                // Handle the exception
                e.printStackTrace()
            }
        }
    }
}
