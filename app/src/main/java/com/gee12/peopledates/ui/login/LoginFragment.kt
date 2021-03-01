package com.gee12.peopledates.ui.login

import androidx.lifecycle.Observer
import androidx.annotation.StringRes
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.gee12.peopledates.BuildConfig

import com.gee12.peopledates.R
import com.gee12.peopledates.ui.BaseFragment
import com.gee12.peopledates.ui.afterTextChanged
import com.gee12.peopledates.ui.getNavigationController
import com.gee12.peopledates.ui.person.PersonsFragmentDirections
import javax.inject.Inject

class LoginFragment : BaseFragment() {

/*    companion object {
        @JvmStatic
        fun newInstance() = LoginFragment()
    }*/

    @Inject
    lateinit var loginViewModel: LoginViewModel

    private var usernameEditText: EditText? = null
    private var passwordEditText: EditText? = null
    private var loginButton: Button? = null
    private var loadingProgressBar: ProgressBar? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*loginViewModel = ViewModelProvider(this,
            ViewModelFactory(requireContext().applicationContext))
            .get(LoginViewModel::class.java)*/

        initViews(view)

        if (BuildConfig.DEBUG) {
            usernameEditText?.setText("gee12")
            passwordEditText?.setText("iHMy5~sv62")
            loginButton?.isEnabled = true
        }

        loginViewModel.loginFormState.observe(viewLifecycleOwner, Observer { handleLoginFormState(it) })
        loginViewModel.loginResult.observe(viewLifecycleOwner, Observer { handleLoginResult(it) })
    }

    private fun initViews(view: View) {
        usernameEditText = view.findViewById(R.id.username)
        passwordEditText = view.findViewById(R.id.password)
        loginButton = view.findViewById(R.id.login)
        loadingProgressBar = view.findViewById(R.id.loading)

        usernameEditText?.afterTextChanged { loginDataChanged() }
        passwordEditText?.afterTextChanged { loginDataChanged() }
        passwordEditText?.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                login()
            }
            false
        }

        loginButton?.setOnClickListener {
            loadingProgressBar?.visibility = View.VISIBLE
            login()
        }
    }

    private fun handleLoginFormState(loginFormState: LoginFormState?): Boolean {
        loginFormState ?: return true
        loginButton?.isEnabled = loginFormState.isDataValid
        loginFormState.usernameError?.let {
            usernameEditText?.error = getString(it)
        }
        loginFormState.passwordError?.let {
            passwordEditText?.error = getString(it)
        }
        return false
    }

    private fun handleLoginResult(loginResult: LoginResult?): Boolean {
        loginResult ?: return true
        loadingProgressBar?.visibility = View.GONE
        loginResult.error?.let {
            showLoginFailed(it)
        }
        loginResult.success?.let {
            handleUserLogin(it)
        }
        return false
    }

    private fun loginDataChanged() {
        loginViewModel.loginDataChanged(
            usernameEditText?.text.toString(),
            passwordEditText?.text.toString()
        )
    }

    private fun login() {
        loginViewModel.login(
            usernameEditText?.text.toString(),
            passwordEditText?.text.toString()
        )
    }

    private fun handleUserLogin(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome) + model.displayName
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, welcome, Toast.LENGTH_LONG).show()

//        getNavigationController().loginSuccess()
        findNavController()
            .navigate(LoginFragmentDirections.showPersonsFragment())
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, errorString, Toast.LENGTH_LONG).show()
    }
}