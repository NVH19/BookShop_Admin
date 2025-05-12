package com.example.bookshop_admin.data.api

import com.example.bookshop_admin.data.model.User
import com.example.bookshop_admin.data.model.request.ProductRequest
import com.example.bookshop_admin.data.model.response.Message
import com.example.bookshop_admin.data.model.response.auth.AuthResponse
import com.example.bookshop_admin.data.model.response.product.ProductResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @FormUrlEncoded
    @POST("customers/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String,
    ): Response<AuthResponse>

    @GET("/customers/all")
    suspend fun getAllCustomer(): Response<List<User>>

    @GET("customers")
    suspend fun getUser(): Response<User>

    @FormUrlEncoded
    @PUT("/customers/update/status")
    suspend fun updateUserStatus(
        @Field("idUser") idUser: Int,
        @Field("status") status: String
    ): Response<Message>

    @GET("/products")
    suspend fun getProducts(
        @Query("limit") limit: Int,
        @Query("page") page: Int,
        @Query("description_length") descriptionLength: Int,
    ): Response<ProductResponse>

    @GET("products/search")
    suspend fun getSearchProducts(
        @Query("limit") limit: Int,
        @Query("page") page: Int,
        @Query("description_length") descriptionLength: Int,
        @Query("query_string") queryString: String,
        @Query("filter_type") filterType: Int,
        @Query("price_sort_order") priceSortOrder: String,
    ): Response<ProductResponse>

    @POST("products/add")
    suspend fun addBook(@Body productRequest: ProductRequest): Response<Message>

    @GET("products/detail/{product_id}")
    suspend fun getBookDetail(@Path("product_id") productId: Int): Response<ProductRequest>

    @PUT("products/update")
    suspend fun updateBook(@Body productRequest: ProductRequest): Response<Message>

    @DELETE("products/delete/{product_id}")
    suspend fun deleteBook(@Path("product_id") productId: Int): Response<Message>

    @GET("products/bestseller")
    suspend fun getBookBestSeller(): Response<ProductResponse>
}