package com.mohit.machinemanagerapi1.data.remote.dto

data class ProductResponseDto(
    val products: List<ProductDto>,
    val limit: Int,
    val skip: Int,
    val total: Int
)