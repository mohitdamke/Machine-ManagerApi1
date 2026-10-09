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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mohit.machinemanagerapi1.presentation.viewmodel.ProductViewModel

@Composable
fun DetailProductList(
    modifier: Modifier = Modifier,
    productId: Int,
    viewModel: ProductViewModel,
    editButton: (Int) -> Unit,
    onBack: ()-> Unit
) {

    val product = viewModel.selectedProducts.collectAsState().value

    LaunchedEffect(productId) {
        viewModel.getProductById(productId)
    }

    if (product == null) {
        CircularProgressIndicator()
        return
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

     Text(product.title)
     Text(product.description)
     Text(product.category)
     Text(product.price.toString())

        Button(onClick = {editButton(product.id)}) { Text("Edit")}
        Button(onClick = {
            viewModel.deleteProduct(product.id)
            onBack()
        }) { Text("Delete")}

    }


}




















