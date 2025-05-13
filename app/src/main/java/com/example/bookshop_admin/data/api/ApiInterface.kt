package com.example.bookshop_admin.data.api

import com.example.bookshop_admin.data.model.OrderDetail
import com.example.bookshop_admin.data.model.User
import com.example.bookshop_admin.data.model.response.Message
import com.example.bookshop_admin.data.model.response.auth.AuthResponse
import com.example.bookshop_admin.data.model.response.order.OrderResponse
import retrofit2.Response
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

    @GET("orders/status/{orderStatusId}")
    suspend fun getAllOrderByOrderStatus(@Path("orderStatusId") orderStatusId: Int): Response<OrderResponse>

    @PUT("orders/status")
    suspend fun updateOrderStatus(
        @Query("orderId") orderId: Int,
        @Query("orderStatusId") orderStatusId: Int
    ): Response<Message>

    @GET("orders/year/{year}")
    suspend fun getOrderByYears(@Path("year") year: Int): Response<OrderResponse>

    @GET("orders/monthOfYear")
    suspend fun getOrderByMonthOfYear(
        @Query("year") year: Int,
        @Query("month") month: Int
    ): Response<OrderResponse>

    @GET("orders/today")
    suspend fun getOrderByToday(@Query("today") today: String): Response<OrderResponse>

    @GET("orders/detail/{orderId}")
    suspend fun getOrderDetail(@Path("orderId") orderId: Int): Response<OrderDetail>
}