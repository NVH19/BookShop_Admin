package com.example.bookshop_admin.data.repository.supplier

import com.example.bookshop_admin.data.model.request.SupplierRequest
import com.example.bookshop_admin.data.model.response.Message
import com.example.bookshop_admin.data.model.response.SupplierResponse
import retrofit2.Response

interface SupplierRepository {
    suspend fun getSuppliers(): Response<SupplierResponse>
    suspend fun addSupplier(supplier: SupplierRequest): Response<Message>
}