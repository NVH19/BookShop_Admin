package com.example.bookshop_admin.datasource

import com.example.bookshop_admin.data.api.RetrofitClient
import com.example.bookshop_admin.data.model.OrderDetail
import com.example.bookshop_admin.data.model.User
import com.example.bookshop_admin.data.model.response.Message
import com.example.bookshop_admin.data.model.response.auth.AuthResponse
import com.example.bookshop_admin.data.model.response.order.OrderResponse
import retrofit2.Response

class RemoteDataSource() : IDataSource  {
    override suspend fun login(email: String, password: String): Response<AuthResponse> {
        return RetrofitClient.apiService.login(email, password)
    }
    override suspend fun getUser(): Response<User>? {
        return RetrofitClient.apiService.getUser()
    }
    override suspend fun getAllCustomer(): Response<List<User>> {
        return RetrofitClient.apiService.getAllCustomer()
    }

    override suspend fun updateUserStatus(idUser: Int, status: String): Response<Message> {
        return RetrofitClient.apiService.updateUserStatus(idUser, status)
    }

    override suspend fun getAllOrderByOrderStatus(orderStatusId: Int): Response<OrderResponse> {
        return RetrofitClient.apiService.getAllOrderByOrderStatus(orderStatusId)
    }

    override suspend fun updateOrderStatus(orderId: Int, orderStatusId: Int): Response<Message> {
        return RetrofitClient.apiService.updateOrderStatus(orderId, orderStatusId)
    }

    override suspend fun getOrdersByYear(year: Int): Response<OrderResponse> {
        return RetrofitClient.apiService.getOrderByYears(year)
    }

    override suspend fun getOrdersByMonthOfYear(year: Int, month: Int): Response<OrderResponse> {
        return RetrofitClient.apiService.getOrderByMonthOfYear(year, month)
    }

    override suspend fun getOrderByToday(today: String): Response<OrderResponse> {
        return RetrofitClient.apiService.getOrderByToday(today)
    }

    override suspend fun getOrderDetail(orderId: Int): Response<OrderDetail>? {
        return RetrofitClient.apiService.getOrderDetail(orderId)
    }
}