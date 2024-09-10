package com.example.fetchproject.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fetchproject.data.model.Item
import com.example.fetchproject.viewmodel.ItemViewModel

@Composable
fun ItemListScreen(viewModel: ItemViewModel = viewModel()) {
    val items by viewModel.items.collectAsState()

    // Sort the listId keys in ascending order
    val sortedItems = items.toSortedMap()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        sortedItems.forEach { (listId, itemList) ->
            // Sort the items within the listId by extracting the numeric part of the name
            val sortedItemList = itemList.sortedBy { item ->
                item.name?.filter { it.isDigit() }?.toIntOrNull() ?: 0
            }

            item {
                ExpandableGroup(listId = listId, items = sortedItemList)
            }
        }
    }
}


@Composable
fun ExpandableGroup(listId: Int, items: List<Item>) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = !expanded }
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "List ID: $listId",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                    contentDescription = if (expanded) "Collapse" else "Expand",
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            if (expanded) {
                Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f))
                items.forEach { item ->
                    Text(
                        text = item.name ?: "Unnamed",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}