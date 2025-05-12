package com.example.bookshop_admin.data.model.response.product

import com.example.bookshop_admin.data.model.Product

data class ProductState (
    val products: List<Product>?,
    val isDefaultState: Boolean
)