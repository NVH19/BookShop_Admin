package com.example.bookshop_admin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bookshop_admin.databinding.ActivityMainBinding
import com.example.bookshop_admin.ui.login.LoginFragment
import com.example.bookshop_admin.utils.MySharedPreferences


class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        MySharedPreferences.init(this)
        setContentView(binding?.root)
        val support = supportFragmentManager.beginTransaction()
        support.replace(R.id.container, LoginFragment()).commit()
    }
}