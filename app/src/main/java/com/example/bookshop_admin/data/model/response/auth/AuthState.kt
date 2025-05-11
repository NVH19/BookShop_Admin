package com.example.bookshop_admin.data.model.response.auth

import com.example.bookshop_admin.data.model.response.error.Error


data class AuthState(
    val error: Error,
    val loginResponse: AuthResponse?,
)