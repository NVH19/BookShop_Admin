package com.example.bookshop_admin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bookshop_admin.databinding.ActivityMainBinding
import com.example.bookshop_admin.ui.main.MainMenuFragment
import com.example.bookshop_admin.ui.main.book.BookFragment

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        val support = supportFragmentManager.beginTransaction()
        support.replace(R.id.container, MainMenuFragment()).commit()
    }
}