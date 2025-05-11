package com.example.bookshop_admin.data.api

import com.example.bookshop_admin.data.model.User
import com.example.bookshop_admin.data.model.response.Message
import com.example.bookshop_admin.data.model.response.auth.AuthResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

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
}