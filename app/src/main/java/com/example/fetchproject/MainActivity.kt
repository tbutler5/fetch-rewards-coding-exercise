package com.example.fetchproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.fetchproject.ui.screens.ItemListScreen
import com.example.fetchproject.ui.theme.FetchProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FetchProjectTheme {
                ItemListScreen()
            }
        }
    }
}
