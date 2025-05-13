package com.example.bookshop_admin.ui.main.order.shippingorder

import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookshop_admin.utils.format.FormatDate
import com.example.bookshop_admin.R
import com.example.bookshop_admin.data.model.Order
import com.example.bookshop_admin.data.model.response.order.OrderHistory
import com.example.bookshop_admin.databinding.FragmentShippingOrderBinding
import com.example.bookshop_admin.ui.adapter.OnItemClickListener
import com.example.bookshop_admin.ui.adapter.OrderAdapter
import com.example.bookshop_admin.ui.main.order.OrderViewModel
import com.example.bookshop_admin.ui.main.order.orderdetail.OrderDetailFragment
import java.time.LocalDateTime

class ShippingOrderFragment : Fragment() {

    companion object {
        fun newInstance() = ShippingOrderFragment()
    }

    private lateinit var viewModel: OrderViewModel
    private var binding: FragmentShippingOrderBinding? = null
    private lateinit var adapter: OrderAdapter
    private var formatDate = FormatDate()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[OrderViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShippingOrderBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = OrderAdapter(2)
        initViewModel()
        binding?.apply {
//            loadingLayout.root.visibility = View.VISIBLE
            viewModel.getAllOrderByOrderStatus(1)
            recyclerOrderShipping.layoutManager = LinearLayoutManager(context)
            recyclerOrderShipping.adapter = adapter
        }
        updateStatus()
        navToOrderDetail()
    }

    private fun updateStatus() {
        adapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                val order = adapter.getOrder(position)
                order?.orderId?.let {
                    viewModel.updateOrderStatus(it, 3)
                }
                Handler().postDelayed({
                    viewModel.getAllOrderByOrderStatus(2)
                }, 300)
            }

        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initViewModel() {
        val list = mutableListOf<OrderHistory>()
        val currentDate = formatDate.formatDate(LocalDateTime.now().toString())
        val mapOrder: MutableMap<String, MutableList<Order>> = mutableMapOf()
        viewModel.order.observe(viewLifecycleOwner) {
            if (it != null) {
                mapOrder.clear()
                for (order in it) {
                    val date: String = if (order.shippedOn == null) {
                        formatDate.formatDate(order.createdOn)
                    } else {
                        formatDate.formatDate(order.shippedOn)
                    }
                    if (date == currentDate) {
                        mapOrder.computeIfAbsent("Hôm nay") { mutableListOf() }.add(order)
                    } else {
                        mapOrder.computeIfAbsent(date) { mutableListOf() }.add(order)
                    }
                }
                list.clear()
                for ((key, value) in mapOrder) {
                    list.add(OrderHistory(key, null))
                    for (values in value) {
                        list.add(OrderHistory(null, values))
                    }
                }
//                if (list.isEmpty()) {
//                    binding?.textOrderHistory?.visibility = View.VISIBLE
//                }
                adapter.setData(list)
//                binding?.loadingLayout?.root?.visibility = View.INVISIBLE
            }
        }
    }

    private fun navToOrderDetail() {
        adapter.setOnItemDetailClickListener(object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                val orderId = adapter.getOrder(position)?.orderId
                val orderStatus = adapter.getOrder(position)?.orderStatus
                val bundle = Bundle()
                bundle.putString("orderId", orderId.toString())
                bundle.putString("orderStatus", orderStatus)
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, OrderDetailFragment().apply { arguments = bundle })
                    .addToBackStack("Orderhistory")
                    .commit()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllOrderByOrderStatus(2)
    }
}