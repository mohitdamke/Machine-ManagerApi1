package com.mohit.machinemanagerapi1.data.repository

import com.mohit.machinemanagerapi1.data.remote.api.ProductApi
import com.mohit.machinemanagerapi1.data.remote.mapper.toProduct
import com.mohit.machinemanagerapi1.data.remote.mapper.toProductDto
import com.mohit.machinemanagerapi1.domain.model.Products
import com.mohit.machinemanagerapi1.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(private val api: ProductApi): ProductRepository {
    override suspend fun getProducts(): List<Products> {
        return api.getProducts().products.map { it.toProduct()  }
    }

    override suspend fun getProductById(id: Int): Products {
        return api.getProductById(id).toProduct()
    }

    override suspend fun addProduct(product: Products): Products {
        return api.addProduct(product.toProductDto()).toProduct()

    }

    override suspend fun updateProduct(
        id: Int,
        product: Products
    ): Products {
        return api.updateProduct(id = id ,product.toProductDto()).toProduct()

    }

    override suspend fun deleteProduct(id: Int): Products {
        return api.deleteProduct(id).toProduct()

    }
}