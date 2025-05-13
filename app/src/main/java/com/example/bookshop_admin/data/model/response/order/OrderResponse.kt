package com.example.bookshop_admin.data.model.response.order

import com.example.bookshop_admin.data.model.Order
import com.google.gson.annotations.SerializedName

data class OrderResponse(
    @SerializedName("count") var count: Int,
    @SerializedName("orders") var orders: List<Order>,
)
