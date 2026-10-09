package com.mohit.machinemanagerapi1.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mohit.machinemanagerapi1.presentation.screens.AddProductList
import com.mohit.machinemanagerapi1.presentation.screens.DetailProductList
import com.mohit.machinemanagerapi1.presentation.screens.EditProductList
import com.mohit.machinemanagerapi1.presentation.screens.ProductList
import com.mohit.machinemanagerapi1.presentation.viewmodel.ProductViewModel

@Composable
fun AppNavigation() {

    val viewmodel : ProductViewModel = hiltViewModel()
    val navController = rememberNavController()

    NavHost(navController, Routes.ProductList) {

        composable(Routes.ProductList){backStack ->

            ProductList(
                viewModel = viewmodel,
                addButton = { navController.navigate(Routes.AddProductList) },
                deleteButton = { viewmodel.deleteProduct(it) },
                detailButton = { navController.navigate(Routes.getDetailScreen(it))},
            )
        }
        composable(Routes.AddProductList){
            AddProductList(
                viewModel = viewmodel,
                onBack = { navController.popBackStack() },
                
            )
        }
        composable(Routes.EditProductList){ backStack ->
            val productId = backStack.arguments?.getString("id")?.toIntOrNull()
            EditProductList(
                viewModel = viewmodel,
                productId = productId ?: 0,
                onBack = { navController.popBackStack() },
            )
        }

        composable(Routes.DetailProductList){ backStack ->
            val productId = backStack.arguments?.getString("id")?.toIntOrNull()

            DetailProductList(
                viewModel = viewmodel,
                productId = productId ?: 0,
                editButton = { navController.navigate(Routes.getEditScreen(it)) },
                onBack = { navController.popBackStack() },

            )
        }

    }


}