package com.mohit.machinemanagerapi1.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohit.machinemanagerapi1.domain.model.Products
import com.mohit.machinemanagerapi1.domain.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val repository: ProductRepository) :
    ViewModel() {

    private val _products = MutableStateFlow<List<Products>>(emptyList())
    val products = _products.asStateFlow()

    var allProducts = mutableListOf<Products>()

    private val _selectedProducts = MutableStateFlow<Products?>(null)
    val selectedProducts = _selectedProducts.asStateFlow()
    private val _searchQuery = MutableStateFlow<String>("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading = _isLoading.asStateFlow()

    private val _isError = MutableStateFlow<String>("")
    val isError = _isError.asStateFlow()

    init {
        getAllProducts()
    }

    fun getAllProducts(forceRefresh: Boolean = false) {

        if (allProducts.isNotEmpty() && !forceRefresh) {
            _products.value = allProducts.toList()
            return
        }

        viewModelScope.launch {
            try {
                _isLoading.value = true
                _isError.value = ""

                val result = repository.getProducts()

                allProducts.clear()
                allProducts.addAll(result)

                _products.value = result

                _isLoading.value = false

            } catch (e: Exception) {
                _isError.value = e.message ?: "Something Went wrong"
            }
        }
    }

    fun searchProduct(query : String){
        _searchQuery.value = query
        if (query.isNotEmpty()){
           _products.value = allProducts.filter { it.title.contains(query, true) }
        } else{
            _products.value = allProducts.toList()
            return
        }
    }

    fun getProductById(id : Int) {

        val localProduct = allProducts.find { it.id == id }
        if (localProduct != null){
            _selectedProducts.value = localProduct
            return
        }

        viewModelScope.launch {
            try {
                _isLoading.value = true
                _isError.value = ""

              _selectedProducts.value = repository.getProductById(id)

                _isLoading.value = false

            } catch (e: Exception) {
                _isError.value = e.message ?: "Something Went wrong"
            }
        }
    }

    fun addProduct(products: Products) {

        viewModelScope.launch {
            try {
                _isLoading.value = true
                _isError.value = ""

              val addedProduct = repository.addProduct(products)

                allProducts.add(0,addedProduct)
                _products.value = allProducts.toList()

                _isLoading.value = false

            } catch (e: Exception) {
                _isError.value = e.message ?: "Something Went wrong"
                _isLoading.value = false

            }
        }
    }

    fun updateProduct(id : Int,products: Products) {

        viewModelScope.launch {
            try {
                _isLoading.value = true
                _isError.value = ""

              val updatedProduct = repository.updateProduct(id,products)

                val index = allProducts.indexOfFirst { it.id == id }
                allProducts[index] = updatedProduct

                _selectedProducts.value = updatedProduct

                _products.value = allProducts.toList()

                _isLoading.value = false

            } catch (e: Exception) {
                _isError.value = e.message ?: "Something Went wrong"
            }
        }
    }

    fun deleteProduct(id : Int) {

        viewModelScope.launch {
            try {
                _isLoading.value = true
                _isError.value = ""

                repository.deleteProduct(id)

                allProducts.removeAll{it.id == id}
                _products.value = allProducts.toList()

                _isLoading.value = false

            } catch (e: Exception) {
                _isError.value = e.message ?: "Something Went wrong"
            }
        }
    }



























}