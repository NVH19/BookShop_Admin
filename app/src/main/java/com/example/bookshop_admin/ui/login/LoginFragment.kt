package com.example.bookshop_admin.ui.login

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bookshop_admin.R
import com.example.bookshop_admin.data.api.RetrofitClient
import com.example.bookshop_admin.data.model.User
import com.example.bookshop_admin.data.model.response.auth.AuthResponse
import com.example.bookshop_admin.databinding.FragmentLoginBinding
import com.example.bookshop_admin.ui.main.MainMenuFragment
import com.example.bookshop_admin.utils.AlertMessageViewer
import com.example.bookshop_admin.utils.LoadingProgressBar
import com.example.bookshop_admin.utils.MySharedPreferences

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel
    private var binding: FragmentLoginBinding? = null
    private var checkVisible = false
    private lateinit var loadingProgressBar: LoadingProgressBar
    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        val accessToken = MySharedPreferences.getAccessToken(null)
        val expiresIn = MySharedPreferences.getLogInTime("ExpiresIn", 0)
        val loginTimeFirst = MySharedPreferences.getLogInTime("FirstTime", 0)
        if (accessToken != null) {
            val loginTime = System.currentTimeMillis()
            navToMainScreen()
            RetrofitClient.updateAccessToken(accessToken)
            if ((loginTime - loginTimeFirst) > expiresIn) {
                MySharedPreferences.clearPreferences()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, LoginFragment()).commit()
                AlertMessageViewer.showAlertDialogMessage(
                    requireContext(),
                    resources.getString(R.string.messageExpiresIn)
                )
            }
        }
        loadingProgressBar = LoadingProgressBar(requireContext())
        binding?.apply {
            layoutSignIn.setOnTouchListener { _, motionEvent ->
                if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                    val event =
                        requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    event.hideSoftInputFromWindow(requireView().windowToken, 0)
                }
                false
            }
            buttonLogin.setOnClickListener {
                val username = editUsername.text.toString()
                val password = editPassword.text.toString()
                val user = AuthResponse(user = User(email = username, password = password))
                viewModel.checkFields(user)
                loadingProgressBar.show()
            }

            imageEye.setOnClickListener {
                val cursorPosition = editPassword.selectionEnd
                if (!checkVisible) {
                    editPassword.transformationMethod =
                        HideReturnsTransformationMethod.getInstance()
                    checkVisible = true
                    imageEye.setImageResource(R.drawable.ic_hide_eye)
                } else {
                    editPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                    checkVisible = false
                    imageEye.setImageResource(R.drawable.ic_visible_eye)
                }
                if (cursorPosition >= 0) {
                    editPassword.setSelection(cursorPosition)
                }
            }
        }
    }

    fun initViewModel() {
        viewModel.loginResponse.observe(viewLifecycleOwner) {
            loadingProgressBar.cancel()
            if (it?.loginResponse == null) {
                it.error.message.let { it1 ->
                    if (it1 != null) {
                        AlertMessageViewer.showAlertDialogMessage(
                            requireContext(),
                            it1
                        )
                    }
                }
            } else {
                if(it.loginResponse.user.role.equals("admin")){
                    navToMainScreen()
                    MySharedPreferences.putAccessToken(it.loginResponse.accessToken)
                    val expiresIn = it.loginResponse.expiresIn.split(" ")[0].toLong()
                    MySharedPreferences.putLogInTime("ExpiresIn", expiresIn * 3600000)
                    MySharedPreferences.putLogInTime("FirstTime", System.currentTimeMillis())
                    RetrofitClient.updateAccessToken(it.loginResponse.accessToken)
                }else{
                    AlertMessageViewer.showAlertDialogMessage(
                        requireContext(),
                        resources.getString(R.string.unauthorized)
                    )
                }
            }
        }
    }

    fun navToMainScreen() {
        parentFragmentManager.beginTransaction().replace(R.id.container, MainMenuFragment())
            .commit()
    }
}