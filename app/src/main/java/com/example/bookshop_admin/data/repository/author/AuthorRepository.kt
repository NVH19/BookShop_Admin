package com.example.bookshop_admin.data.repository.author

import com.example.bookshop_admin.data.model.request.AuthorRequest
import com.example.bookshop_admin.data.model.response.Message
import com.example.bookshop_admin.data.model.response.author.AuthorInfor
import com.example.bookshop_admin.data.model.response.author.AuthorResponse
import retrofit2.Response

interface AuthorRepository {
    suspend fun getAuthor(limit: Int, page: Int): Response<AuthorResponse>

    suspend fun addAuthor(author: AuthorRequest): Response<Message>

    suspend fun getAuthor(authorId: Int): Response<AuthorInfor>?
}