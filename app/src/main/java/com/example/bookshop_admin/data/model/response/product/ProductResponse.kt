package com.example.bookshop_admin.data.model.response.product

import com.example.bookshop_admin.data.model.Product
import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("count")
    var count: Int?,
    @SerializedName("rows")
    var products: List<Product>,
)