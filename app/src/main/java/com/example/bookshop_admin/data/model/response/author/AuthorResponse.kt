package com.example.bookshop_admin.data.model.response.author

import com.example.bookshop_admin.data.model.Author
import com.google.gson.annotations.SerializedName

data class AuthorResponse(
    @SerializedName("authors") var authors: List<Author>,
)