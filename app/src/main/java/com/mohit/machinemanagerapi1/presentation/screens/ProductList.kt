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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mohit.machinemanagerapi1.presentation.viewmodel.ProductViewModel

@Composable
fun ProductList(
    modifier: Modifier = Modifier,
    viewModel: ProductViewModel,
    addButton: () -> Unit,
    detailButton: (Int) -> Unit,
    deleteButton: (Int) -> Unit
) {

    val product = viewModel.products.collectAsState().value
    val searchQuery = viewModel.searchQuery.collectAsState().value
    val isLoading = viewModel.isLoading.collectAsState().value
    val isError = viewModel.isError.collectAsState().value


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(
            value = searchQuery,
            onValueChange = viewModel::searchProduct
        )
        Spacer(Modifier.heightIn(6.dp))
        Button(onClick = { viewModel.getAllProducts(forceRefresh = true) }) {
            Text("Refresh")
        }
        Spacer(Modifier.heightIn(6.dp))
        Button(onClick = { addButton() }) {
            Text("Add +")
        }
        Spacer(Modifier.heightIn(6.dp))

        if (isLoading) {
            CircularProgressIndicator()
        } else if (isError.isNotEmpty()) {
            Text("Error $isError")
        } else {

            LazyColumn {
                items(product) {
                    Text(it.title)
                    Text(it.description)
                    Text(it.price.toString())
                    Button(onClick = { detailButton(it.id) }) { Text("View") }
                    Button(onClick = { deleteButton(it.id) }) { Text("delete") }
                    HorizontalDivider()
                    Spacer(Modifier.heightIn(2.dp))


                }
            }

        }

    }


}




















