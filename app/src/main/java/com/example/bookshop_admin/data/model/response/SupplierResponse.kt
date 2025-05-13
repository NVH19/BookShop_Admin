package com.example.bookshop_admin.data.model.response

import com.example.bookshop_admin.data.model.Supplier
import com.google.gson.annotations.SerializedName

data class SupplierResponse(
    @SerializedName("count") var count: Int?,
    @SerializedName("rows")
    var suppliers: List<Supplier>,
)