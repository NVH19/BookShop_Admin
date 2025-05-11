package com.example.bookshop_admin.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookshop_admin.data.model.response.auth.AuthResponse
import com.example.bookshop_admin.data.model.response.auth.AuthState
import com.example.bookshop_admin.data.model.response.error.ErrorResponse
import com.example.bookshop_admin.data.repository.user.UserRepository
import com.example.bookshop_admin.data.repository.user.UserRepositoryImp
import com.example.bookshop_admin.datasource.RemoteDataSource
import com.example.bookshop_admin.data.model.response.error.Error
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private var _loginResponse = MutableLiveData<AuthState>()
    val loginResponse: LiveData<AuthState> get() = _loginResponse
    private val authRepository: UserRepository = UserRepositoryImp(RemoteDataSource())
    fun checkFields(user: AuthResponse) {

        if (user.isSignInFieldEmpty()) {
            _loginResponse.postValue(
                AuthState(
                    Error(message = "Các trường không được để trống!"),
                    null
                )
            )
            return
        }

        if (!user.isValidEmail()) {
            _loginResponse.postValue(
                AuthState(
                    Error(message = "Vui lòng nhập địa chỉ email của bạn!"),
                    null
                )
            )
            return
        }

        if (!user.isPasswordGreaterThan5(user.user.password)) {
            _loginResponse.postValue(
                AuthState(
                    Error(
                        message =
                        "Mật khẩu phải dài hơn 5 kí tự bao gồm cả chữ và số"
                    ),
                    null
                )
            )
            return
        }
        checkLogin(user)

    }

    fun checkLogin(user: AuthResponse) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = authRepository.login(user.user.email, user.user.password)
            if (response?.isSuccessful == true ) {
                _loginResponse.postValue(
                    AuthState(
                        Error(message = "Đăng nhập thành công!"),
                        response.body()
                    )
                )
            } else {
                val errorBody = response?.errorBody()?.string()
                val gson = Gson()
                val errorResponse = gson.fromJson(errorBody, ErrorResponse::class.java)
                _loginResponse.postValue(
                    AuthState(
                        Error(message = errorResponse.error.message),
                        null
                    )
                )
                Log.d("LoginNull", "NULL")
            }
        }
    }
}