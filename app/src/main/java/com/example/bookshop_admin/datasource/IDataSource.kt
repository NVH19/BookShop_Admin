package com.example.bookshop_admin.datasource

import com.example.bookshop_admin.data.model.User
import com.example.bookshop_admin.data.model.request.AuthorRequest
import com.example.bookshop_admin.data.model.request.CategoryRequest
import com.example.bookshop_admin.data.model.request.ProductRequest
import com.example.bookshop_admin.data.model.request.SupplierRequest
import com.example.bookshop_admin.data.model.response.Message
import com.example.bookshop_admin.data.model.response.SupplierResponse
import com.example.bookshop_admin.data.model.response.auth.AuthResponse
import com.example.bookshop_admin.data.model.response.author.AuthorInfor
import com.example.bookshop_admin.data.model.response.author.AuthorResponse
import com.example.bookshop_admin.data.model.response.category.CategoryBestSeller
import com.example.bookshop_admin.data.model.response.category.CategoryResponse
import com.example.bookshop_admin.data.model.response.product.ProductResponse
import retrofit2.Response

interface IDataSource {
    suspend fun login(email: String, password: String): Response<AuthResponse>
    suspend fun getUser(): Response<User>?
    suspend fun getAllCustomer(): Response<List<User>>
    suspend fun updateUserStatus(idUser: Int, status: String): Response<Message>

    suspend fun getProducts(limit: Int, page: Int, description: Int): Response<ProductResponse>
    suspend fun getSearchProducts(
        limit: Int,
        page: Int,
        description: Int,
        query: String,
    ): Response<ProductResponse>

    suspend fun addBook(productRequest: ProductRequest): Response<Message>

    suspend fun updateBook(productRequest: ProductRequest): Response<Message>

    suspend fun deleteBook(productId: Int): Response<Message>
    suspend fun getBookDetail(productId: Int): Response<ProductRequest>

    suspend fun getBookBestSeller(): Response<ProductResponse>

    suspend fun getAuthors(limit: Int, page: Int): Response<AuthorResponse>

    suspend fun getAuthor(authorId: Int): Response<AuthorInfor>?

    suspend fun addAuthor(author: AuthorRequest): Response<Message>

    suspend fun getCategories(): Response<CategoryResponse>

    suspend fun getCategoryBestSeller(): Response<List<CategoryBestSeller>>

    suspend fun addCategory(category: CategoryRequest): Response<Message>

    suspend fun getSuppliers(): Response<SupplierResponse>

    suspend fun addSupplier(supplier: SupplierRequest): Response<Message>
}