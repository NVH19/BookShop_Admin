package com.example.bookshop_admin.data.model.response.order

import com.example.bookshop_admin.data.model.Order

data class OrderHistory(
    val header: String?,
    val order: Order?,
)