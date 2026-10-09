package com.mohit.machinemanagerapi1.data.remote.mapper

import android.R.attr.rating
import com.mohit.machinemanagerapi1.data.remote.dto.ProductDto
import com.mohit.machinemanagerapi1.domain.model.Products

fun ProductDto.toProduct(): Products {
    return Products(
        id = id,
        title = title,
        description = description,
        category = category,
        price = price,
    )
}
fun Products.toProductDto(): ProductDto {
    return ProductDto(
        id = id,
        title = title,
        description = description,
        category = category,
        price = price,
    )
}