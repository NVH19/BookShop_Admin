package com.example.bookshop_admin.data.model.response.author

import com.google.gson.annotations.SerializedName

data class AuthorProInfo(
    @SerializedName("author_id") var authorId: Int,
    @SerializedName("author_name") var authorName: String,
)