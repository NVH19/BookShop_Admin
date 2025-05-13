package com.example.bookshop_admin.datasource

import com.example.bookshop_admin.data.model.OrderDetail
import com.example.bookshop_admin.data.model.User
import com.example.bookshop_admin.data.model.response.Message
import com.example.bookshop_admin.data.model.response.auth.AuthResponse
import com.example.bookshop_admin.data.model.response.order.OrderResponse
import retrofit2.Response

interface IDataSource {
    suspend fun login(email: String, password: String): Response<AuthResponse>
    suspend fun getUser(): Response<User>?
    suspend fun getAllCustomer(): Response<List<User>>
    suspend fun updateUserStatus(idUser: Int, status: String): Response<Message>

    suspend fun getAllOrderByOrderStatus(orderStatusId: Int): Response<OrderResponse>

    suspend fun updateOrderStatus(orderId: Int, orderStatusId: Int): Response<Message>

    suspend fun getOrdersByYear(year: Int): Response<OrderResponse>

    suspend fun getOrdersByMonthOfYear(year: Int, month: Int): Response<OrderResponse>

    suspend fun getOrderByToday(today: String): Response<OrderResponse>

    suspend fun getOrderDetail(orderId: Int): Response<OrderDetail>?
}