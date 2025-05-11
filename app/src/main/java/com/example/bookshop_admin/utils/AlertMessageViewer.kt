package com.example.bookshop_admin.utils

import android.app.AlertDialog
import android.content.Context

object AlertMessageViewer {
    fun showAlertDialogMessage(context: Context, message: String) {
        AlertDialog.Builder(context)
            .setTitle(null)
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton("Close") { dialog, _ ->
                dialog.cancel()
            }
            .show()
    }
}