package com.example.bookshop_admin.ui.main.user

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookshop_admin.data.model.User
import com.example.bookshop_admin.data.model.response.Message
import com.example.bookshop_admin.data.repository.user.UserRepository
import com.example.bookshop_admin.data.repository.user.UserRepositoryImp
import com.example.bookshop_admin.datasource.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private var _customers = MutableLiveData<List<User>>()
    val customers: LiveData<List<User>> get() = _customers
    private val _emailAdmin = MutableLiveData<String>()
    val emailAdmin: LiveData<String> get() = _emailAdmin
    private var _message = MutableLiveData<Message>()
    val message: LiveData<Message> get() = _message
    private val userRepo: UserRepository = UserRepositoryImp(RemoteDataSource())
    fun getAllCustomer() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = userRepo.getAllCustomer()
            if (response.isSuccessful) {
                _customers.postValue(
                    response.body()
                )
            } else {
                Log.d("GetCustomerNumber", "NULL")
            }
        }
    }
    fun getUser() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = userRepo.getUser()
            if (response?.isSuccessful == true) {
                _emailAdmin.postValue(response.body()?.email)
            } else {
                Log.d("getEmailAdmin", "NULL")
            }
        }
    }
    fun updateUserStatus(idUser: Int, status: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = userRepo.updateUserStatus(idUser, status)
            if (response.isSuccessful) {
                _message.postValue(response.body())
            } else {
                Log.d("UpdateUserStatus", "NULL")
            }
        }
    }
}