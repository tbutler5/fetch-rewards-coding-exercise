package com.example.fetchproject

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.fetchproject.data.model.Item
import com.example.fetchproject.data.network.ApiService
import com.example.fetchproject.viewmodel.ItemViewModel
import io.mockk.coEvery
import io.mockk.mockkObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ItemViewModelTest {

    // Rule needed to test LiveData/StateFlow components
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: ItemViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        // Set the main dispatcher for coroutines to a test dispatcher
        Dispatchers.setMain(testDispatcher)

        // Mock the ApiService object
        mockkObject(ApiService)

        // Initialize the ViewModel
        viewModel = ItemViewModel()
    }

    @Test
    fun `test fetchItems groups and filters correctly`() = runTest {
        // Mocking the API response
        val mockItems = listOf(
            Item(1, "Item 1"),
            Item(1, "Item 2"),
            Item(2, null),  // This item will be filtered out
            Item(2, "Item 3"),
            Item(3, "")     // This item will be filtered out
        )

        // Mock ApiService.fetchItems to return the mockItems
        coEvery { ApiService.fetchItems() } returns mockItems

        // Call the fetchItems function to populate the StateFlow
        viewModel.fetchItems()

        // Move the dispatcher to execute all pending coroutines
        testDispatcher.scheduler.advanceUntilIdle()

        // Now check the value inside StateFlow
        val expected = mapOf(
            1 to listOf(Item(1, "Item 1"), Item(1, "Item 2")),
            2 to listOf(Item(2, "Item 3"))
        )

        assertEquals(expected, viewModel.items.value)
    }
}
