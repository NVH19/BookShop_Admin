package com.example.bookshop_admin.utils

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.example.bookshop_admin.R

class LoadingProgressBar(context: Context) : Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_loading)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        setCancelable(false)
    }
}