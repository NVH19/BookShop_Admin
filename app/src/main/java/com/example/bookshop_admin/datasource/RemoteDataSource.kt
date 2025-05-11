package com.example.bookshop_admin.datasource

import com.example.bookshop_admin.data.api.RetrofitClient
import com.example.bookshop_admin.data.model.User
import com.example.bookshop_admin.data.model.response.Message
import com.example.bookshop_admin.data.model.response.auth.AuthResponse
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
}