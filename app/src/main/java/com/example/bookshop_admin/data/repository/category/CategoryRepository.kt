package com.example.bookshop_admin.data.repository.category

import com.example.bookshop_admin.data.model.request.CategoryRequest
import com.example.bookshop_admin.data.model.response.Message
import com.example.bookshop_admin.data.model.response.category.CategoryBestSeller
import com.example.bookshop_admin.data.model.response.category.CategoryResponse
import retrofit2.Response

interface CategoryRepository {
    suspend fun getCategroies(): Response<CategoryResponse>

    suspend fun addCategory(category: CategoryRequest): Response<Message>

    suspend fun getCategoryBestSeller(): Response<List<CategoryBestSeller>>
}