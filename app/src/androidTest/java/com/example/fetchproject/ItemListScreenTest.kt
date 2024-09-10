// File: src/androidTest/java/com/example/fetchproject/ItemListScreenTest.kt

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.fetchproject.data.model.Item
import com.example.fetchproject.ui.screens.ItemListScreen
import org.junit.Rule
import org.junit.Test

class ItemListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testItemListDisplay() {
        val mockItems = listOf(
            Item(1, "Item 1"),
            Item(1, "Item 2"),
            Item(2, "Item 3"),
            Item(3, "Item 4")
        )

        composeTestRule.setContent {
            ItemListScreen()
        }

        // Check if all grouped list headers and items are displayed
        composeTestRule.onNodeWithText("List ID: 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Item 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Item 2").assertIsDisplayed()

        composeTestRule.onNodeWithText("List ID: 2").assertIsDisplayed()
        composeTestRule.onNodeWithText("Item 3").assertIsDisplayed()

        composeTestRule.onNodeWithText("List ID: 3").assertIsDisplayed()
        composeTestRule.onNodeWithText("Item 4").assertIsDisplayed()
    }
}
