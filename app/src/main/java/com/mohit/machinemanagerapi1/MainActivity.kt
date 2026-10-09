package com.mohit.machinemanagerapi1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.mohit.machinemanagerapi1.navigation.AppNavigation
import com.mohit.machinemanagerapi1.ui.theme.MachineManagerApi1Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MachineManagerApi1Theme {
                AppNavigation()
            }
        }
    }
}
