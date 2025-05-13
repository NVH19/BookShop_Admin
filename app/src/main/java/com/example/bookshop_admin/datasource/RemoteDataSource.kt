package com.example.bookshop_admin.datasource

import com.example.bookshop_admin.data.api.RetrofitClient
import com.example.bookshop_admin.data.model.OrderDetail
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

    override suspend fun getAuthors(limit: Int, page: Int): Response<AuthorResponse> {
        return RetrofitClient.apiService.getAllAuthor(limit, page)
    }

    override suspend fun getAuthor(authorId: Int): Response<AuthorInfor>? {
        return RetrofitClient.apiService.getAuthor(authorId)
    }

    override suspend fun addAuthor(author: AuthorRequest): Response<Message> {
        return RetrofitClient.apiService.addAuthor(author)
    }
    override suspend fun getCategories(): Response<CategoryResponse> {
        return RetrofitClient.apiService.getAllCategory()
    }

    override suspend fun getCategoryBestSeller(): Response<List<CategoryBestSeller>> {
        return RetrofitClient.apiService.getCategoryBestSeller()
    }

    override suspend fun addCategory(category: CategoryRequest): Response<Message> {
        return RetrofitClient.apiService.addCategory(category)
    }

    override suspend fun getSuppliers(): Response<SupplierResponse> {
        return RetrofitClient.apiService.getAllSuppliers()
    }

    override suspend fun addSupplier(supplier: SupplierRequest): Response<Message> {
        return RetrofitClient.apiService.addSupplier(supplier)
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