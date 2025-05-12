package com.example.bookshop_admin.data.repository.book

import com.example.bookshop_admin.data.model.request.ProductRequest
import com.example.bookshop_admin.data.model.response.Message
import com.example.bookshop_admin.data.model.response.product.ProductResponse
import com.example.bookshop_admin.datasource.IDataSource
import retrofit2.Response

class BookRepositoryImp(private val iDataSource: IDataSource) : BookRepository {
    override suspend fun getProducts(
        limit: Int,
        page: Int,
        description: Int
    ): Response<ProductResponse> {
        return iDataSource.getProducts(limit, page, description)
    }

    override suspend fun getSearchProducts(
        limit: Int,
        page: Int,
        description: Int,
        query: String
    ): Response<ProductResponse> {
        return iDataSource.getSearchProducts(limit, page, description, query)
    }

    override suspend fun addBook(productRequest: ProductRequest): Response<Message> {
        return iDataSource.addBook(productRequest)
    }

    override suspend fun updateBook(productRequest: ProductRequest): Response<Message> {
        return iDataSource.updateBook(productRequest)
    }

    override suspend fun deleteBook(productId: Int): Response<Message> {
        return iDataSource.deleteBook(productId)
    }

    override suspend fun getBookDetail(productId: Int): Response<ProductRequest> {
        return iDataSource.getBookDetail(productId)
    }

    override suspend fun getBookBestSeller(): Response<ProductResponse> {
        return iDataSource.getBookBestSeller()
    }
}