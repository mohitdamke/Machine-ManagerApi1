package com.mohit.machinemanagerapi1.navigation

object Routes {

    const val ProductList = "product_list"
    const val AddProductList = "add_product_list"
    const val EditProductList = "edit_product_list/{id}"
    const val DetailProductList = "detail_product_list/{id}"
    fun getDetailScreen(id : Int) = "detail_product_list/$id"
    fun getEditScreen(id : Int) = "edit_product_list/$id"
}