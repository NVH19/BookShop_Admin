package com.example.bookshop_admin.datasource

import com.example.bookshop_admin.data.api.RetrofitClient
import com.example.bookshop_admin.data.model.User
import com.example.bookshop_admin.data.model.request.ProductRequest
import com.example.bookshop_admin.data.model.response.Message
import com.example.bookshop_admin.data.model.response.auth.AuthResponse
import com.example.bookshop_admin.data.model.response.product.ProductResponse
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

    override suspend fun getProducts(
        limit: Int,
        page: Int,
        description: Int,
    ): Response<ProductResponse> {
        return RetrofitClient.apiService.getProducts(limit, page, description)
    }

    override suspend fun getSearchProducts(
        limit: Int,
        page: Int,
        description: Int,
        query: String
    ): Response<ProductResponse> {
        return RetrofitClient.apiService.getSearchProducts(
            limit,
            page,
            description,
            query,
            1,
            "asc"
        )
    }

    override suspend fun addBook(productRequest: ProductRequest): Response<Message> {
        return RetrofitClient.apiService.addBook(productRequest)
    }

    override suspend fun updateBook(productRequest: ProductRequest): Response<Message> {
        return RetrofitClient.apiService.updateBook(productRequest)
    }

    override suspend fun getBookDetail(productId: Int): Response<ProductRequest> {
        return RetrofitClient.apiService.getBookDetail(productId)
    }

    override suspend fun deleteBook(productId: Int): Response<Message> {
        return RetrofitClient.apiService.deleteBook(productId)
    }

    override suspend fun getBookBestSeller(): Response<ProductResponse> {
        return RetrofitClient.apiService.getBookBestSeller()
    }
}