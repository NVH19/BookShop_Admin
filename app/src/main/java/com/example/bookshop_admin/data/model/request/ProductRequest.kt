package com.example.bookshop_admin.data.model.request

import android.text.TextUtils
import com.example.bookshop_admin.data.model.Author
import com.example.bookshop_admin.data.model.Category
import com.example.bookshop_admin.data.model.Supplier
import java.io.Serializable

data class ProductRequest(
    var id: Int? = null,
    var name: String?,
    var description: String?,
    var price: String,
    var discounted_price: String,
    var quantity: Int = 0,
    var image: String? = null,
    var image_2: String? = null,
    var quantitySold: Int = 0,
    var thumbnail: String? = null,
    var author: Author? = null,
    var supplier: Supplier? = null,
    var category: Category? = null,
    var isBannerSelected: Boolean = false,
    var fileName: String? = null,
    var banner: String? = null
) : Serializable {
    fun isFieldEmpty(): Boolean {
        return TextUtils.isEmpty(name) || TextUtils.isEmpty(description) || quantity == 0 || price == "0.0"
                || TextUtils.isEmpty(image) || TextUtils.isEmpty(author?.authorName) || TextUtils.isEmpty(
            supplier?.name
        )
                || TextUtils.isEmpty(price) || TextUtils.isEmpty(
            category?.name
        )
    }

    fun isPriceThanDiscountedPrice(): Boolean {
        return price.toDouble().toInt() > discounted_price.toDouble().toInt()
    }
}