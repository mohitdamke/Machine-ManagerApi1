package com.mohit.machinemanagerapi1.domain.repository

import com.mohit.machinemanagerapi1.domain.model.Products

interface ProductRepository {

    suspend fun getProducts(): List<Products>

    suspend fun getProductById( id: Int): Products

    suspend fun addProduct( product: Products): Products

    suspend fun updateProduct( id : Int, product: Products): Products

    suspend fun deleteProduct( id : Int): Products

}