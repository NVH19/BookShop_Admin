package com.example.bookshop_admin.data.repository.supplier

import com.example.bookshop_admin.data.model.request.SupplierRequest
import com.example.bookshop_admin.data.model.response.Message
import com.example.bookshop_admin.data.model.response.SupplierResponse
import com.example.bookshop_admin.datasource.IDataSource
import retrofit2.Response

class SupplierRepositoryImp(private val iDataSource: IDataSource) : SupplierRepository {
    override suspend fun getSuppliers(): Response<SupplierResponse> {
        return iDataSource.getSuppliers()
    }

    override suspend fun addSupplier(supplier: SupplierRequest): Response<Message> {
        return iDataSource.addSupplier(supplier)
    }
}