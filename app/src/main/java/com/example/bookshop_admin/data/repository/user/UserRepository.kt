package com.example.bookshop_admin.data.repository.user

import com.example.bookshop_admin.data.model.User
import com.example.bookshop_admin.data.model.response.Message
import com.example.bookshop_admin.data.model.response.auth.AuthResponse
import retrofit2.Response

interface UserRepository {
    suspend fun login(email: String, password: String): Response<AuthResponse>
    suspend fun getUser(): Response<User>?
    suspend fun getAllCustomer(): Response<List<User>>
    suspend fun updateUserStatus(idUser: Int, status: String): Response<Message>
}