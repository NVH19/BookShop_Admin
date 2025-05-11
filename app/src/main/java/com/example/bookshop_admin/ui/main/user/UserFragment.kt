package com.example.bookshop_admin.ui.main.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookshop_admin.databinding.FragmentUserBinding
import com.example.bookshop_admin.ui.adapter.OnItemClickListener
import com.example.bookshop_admin.ui.adapter.UserAdapter
import com.example.bookshop_admin.utils.AlertMessageViewer
import com.example.bookshop_admin.utils.LoadingProgressBar

class UserFragment : Fragment() {

    companion object {
        fun newInstance() = UserFragment()
    }

    private lateinit var viewModel: UserViewModel
    private var binding: FragmentUserBinding? = null
    private lateinit var adapter: UserAdapter
    private lateinit var loadingProgressBar: LoadingProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[UserViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = UserAdapter()
        initViewModel()
        loadingProgressBar = LoadingProgressBar(requireContext())
        loadingProgressBar.show()
        viewModel.getAllCustomer()
        binding?.apply {
            recyclerUser.layoutManager = LinearLayoutManager(context)
            recyclerUser.adapter = adapter
        }
        lockUser()
    }

    private fun initViewModel() {
        viewModel.customers.observe(viewLifecycleOwner) {
            adapter.setData(it)
            loadingProgressBar.cancel()
        }
        viewModel.message.observe(viewLifecycleOwner) {
            AlertMessageViewer.showAlertDialogMessage(requireContext(), it.message)
        }
    }

    private fun lockUser() {
        adapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                val user = adapter.getUser(position)
                if (user.status.equals("active")) {
                    user.userId?.let { viewModel.updateUserStatus(it, "inactive") }
                    adapter.setStatus(position, "inactive")
                } else {
                    user.userId?.let { viewModel.updateUserStatus(it, "active") }
                    adapter.setStatus(position, "active")
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllCustomer()
    }
}
