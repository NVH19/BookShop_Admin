package com.example.bookshop_admin.data.repository.category

import com.example.bookshop_admin.data.model.request.CategoryRequest
import com.example.bookshop_admin.data.model.response.Message
import com.example.bookshop_admin.data.model.response.category.CategoryBestSeller
import com.example.bookshop_admin.data.model.response.category.CategoryResponse
import com.example.bookshop_admin.datasource.IDataSource
import retrofit2.Response

class CategoryRepositoryImp(private val iDataSource: IDataSource) : CategoryRepository {
    override suspend fun getCategroies(): Response<CategoryResponse> {
        return iDataSource.getCategories()
    }

    override suspend fun addCategory(category: CategoryRequest): Response<Message> {
        return iDataSource.addCategory(category)
    }

    override suspend fun getCategoryBestSeller(): Response<List<CategoryBestSeller>> {
        return iDataSource.getCategoryBestSeller()
    }
}