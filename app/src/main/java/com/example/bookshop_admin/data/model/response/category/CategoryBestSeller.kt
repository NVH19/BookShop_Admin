package com.example.bookshop_admin.data.model.response.category

import com.example.bookshop_admin.data.model.Category
import com.google.gson.annotations.SerializedName

data class CategoryBestSeller(
    @SerializedName("category") var category: Category,
    @SerializedName("totalSold") var totalSold: Long,
)
