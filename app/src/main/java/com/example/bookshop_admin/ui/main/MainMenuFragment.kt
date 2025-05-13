package com.example.bookshop_admin.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.bookshop_admin.R
import com.example.bookshop_admin.databinding.FragmentMainMenuBinding
import com.example.bookshop_admin.ui.adapter.ViewPager2Adapter
import com.example.bookshop_admin.ui.main.book.BookFragment
import com.example.bookshop_admin.ui.main.user.UserFragment

class MainMenuFragment : Fragment() {

    private lateinit var binding: FragmentMainMenuBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMainMenuBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val fragments = listOf(
            BookFragment(),
            OrderFragmentTabLayout(),
            UserFragment(),
        )
        val adapter = ViewPager2Adapter(requireActivity(), fragments)
        binding.viewPager.adapter = adapter
        binding.viewPager.isUserInputEnabled = false
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> binding.navigation.menu.findItem(R.id.menu_book).isChecked = true
                    1 -> binding.navigation.menu.findItem(R.id.menu_order).isChecked = true
                    2 -> binding.navigation.menu.findItem(R.id.menu_user).isChecked = true
                }
            }
        })
        binding.apply {
            navigation.setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.menu_book -> viewPager.currentItem = 0
                    R.id.menu_order -> viewPager.currentItem = 1
                    R.id.menu_user -> viewPager.currentItem = 2
                }
                false
            }
        }
    }
}