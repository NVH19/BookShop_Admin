package com.example.bookshop_admin.ui.main.order

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookshop_admin.data.model.Order
import com.example.bookshop_admin.data.model.response.Message
import com.example.bookshop_admin.data.repository.order.OrderRepository
import com.example.bookshop_admin.data.repository.order.OrderRepositoryImp
import com.example.bookshop_admin.datasource.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrderViewModel : ViewModel() {
    private val _order = MutableLiveData<List<Order>>()
    val order: MutableLiveData<List<Order>> get() = _order
    private val _message = MutableLiveData<Message>()
    val message: LiveData<Message> get() = _message
    private var orderRepository: OrderRepository = OrderRepositoryImp(RemoteDataSource())

    fun getAllOrderByOrderStatus(orderStatusId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = orderRepository.getAllOrderByOrderStatus(orderStatusId)
            if (response?.isSuccessful == true) {
                _order.postValue(response.body()?.orders)
            } else {
                Log.d("GetOrderHistory", "NULLL")
            }
        }
    }
    fun updateOrderStatus(orderId:Int, orderStatusId: Int){
        viewModelScope.launch(Dispatchers.IO){
            val response=orderRepository.updateOrderStatus(orderId, orderStatusId)
            if(response.isSuccessful){
                _message.postValue(response.body())
            }else{
                Log.d("UpdateOrderStatus", "NULL")
            }
        }
    }
}