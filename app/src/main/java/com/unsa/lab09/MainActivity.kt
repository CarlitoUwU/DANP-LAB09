package com.unsa.lab09

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.unsa.lab09.ui.AppNavigation
import com.unsa.lab09.ui.theme.Lab09Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab09Theme {
                AppNavigation()
            }
        }
    }
}