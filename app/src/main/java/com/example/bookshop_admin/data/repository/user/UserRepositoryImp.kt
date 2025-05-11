package com.example.bookshop_admin.data.repository.user

import com.example.bookshop_admin.data.model.User
import com.example.bookshop_admin.data.model.response.Message
import com.example.bookshop_admin.data.model.response.auth.AuthResponse
import com.example.bookshop_admin.datasource.IDataSource
import retrofit2.Response

class UserRepositoryImp(private val iDataSource: IDataSource) : UserRepository {

    override suspend fun login(email: String, password: String): Response<AuthResponse> {
        return iDataSource.login(email, password)
    }
    override suspend fun getUser(): Response<User>? {
        return iDataSource.getUser()
    }

    override suspend fun getAllCustomer(): Response<List<User>> {
        return iDataSource.getAllCustomer()
    }

    override suspend fun updateUserStatus(idUser: Int, status: String): Response<Message> {
        return iDataSource.updateUserStatus(idUser, status)
    }
}