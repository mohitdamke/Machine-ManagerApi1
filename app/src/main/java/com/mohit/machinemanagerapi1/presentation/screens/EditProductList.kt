package com.mohit.machinemanagerapi1.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mohit.machinemanagerapi1.domain.model.Products
import com.mohit.machinemanagerapi1.presentation.viewmodel.ProductViewModel

@Composable
fun EditProductList(
    modifier: Modifier = Modifier,
    productId: Int,
    viewModel: ProductViewModel,
    onBack: () -> Unit,
) {

    val selectedProducts = viewModel.selectedProducts.collectAsState().value
    val isLoading = viewModel.isLoading.collectAsState().value
    val isError = viewModel.isError.collectAsState().value

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }

    LaunchedEffect(productId) {
        viewModel.getProductById(productId)
    }

    LaunchedEffect(selectedProducts) {
        selectedProducts?.let {
            title = it.title
            description = it.description
            category = it.category
            price = it.price.toString()

        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isLoading) {
            CircularProgressIndicator()
        }
        if (isError.isNotEmpty()) {
            Text("Error $isError")
        }

        OutlinedTextField(
            value = title,
            onValueChange = { title = it }
        )

        OutlinedTextField(
            value = description,
            onValueChange = { description = it }
        )

        OutlinedTextField(
            value = category,
            onValueChange = { category = it }
        )

        OutlinedTextField(
            value = price,
            onValueChange = { price = it }
        )
        Button(onClick = {
            selectedProducts?.let{
                viewModel.updateProduct(
                    id = productId,
                    products = Products(
                        id = productId,
                        title = title,
                        description = description,
                        category = category,
                        price = price.toDouble()
                    )
                )
            }
            onBack()
        }) { Text("Update Product") }


    }


}




















