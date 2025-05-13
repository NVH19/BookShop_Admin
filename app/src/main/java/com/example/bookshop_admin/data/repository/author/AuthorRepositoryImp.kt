package com.example.bookshop_admin.data.repository.author

import com.example.bookshop_admin.data.model.request.AuthorRequest
import com.example.bookshop_admin.data.model.response.Message
import com.example.bookshop_admin.data.model.response.author.AuthorInfor
import com.example.bookshop_admin.data.model.response.author.AuthorResponse
import com.example.bookshop_admin.datasource.IDataSource
import retrofit2.Response

class AuthorRepositoryImp(private val iDataSource: IDataSource) : AuthorRepository {
    override suspend fun getAuthor(limit: Int, page: Int): Response<AuthorResponse> {
        return iDataSource.getAuthors(limit, page)
    }

    override suspend fun addAuthor(author: AuthorRequest): Response<Message> {
        return iDataSource.addAuthor(author)
    }

    override suspend fun getAuthor(authorId: Int): Response<AuthorInfor>? {
        return iDataSource.getAuthor(authorId)
    }
}