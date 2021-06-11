package com.dunghn.tranningkotlin.ui.auth.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.dunghn.tranningkotlin.data.network.AuthApi
import com.dunghn.tranningkotlin.data.network.Resource
import com.dunghn.tranningkotlin.data.repository.AuthRepository
import com.dunghn.tranningkotlin.databinding.FragmentLoginBinding
import com.dunghn.tranningkotlin.ui.base.BaseFragment
import com.dunghn.tranningkotlin.ui.home.HomeActivity
import com.dunghn.tranningkotlin.util.enable
import com.dunghn.tranningkotlin.util.handleApiError
import com.dunghn.tranningkotlin.util.startNewActivity
import com.dunghn.tranningkotlin.util.visible
import kotlinx.coroutines.launch


class LoginFragment :
    BaseFragment<LoginViewModel, FragmentLoginBinding, AuthRepository>() {

    override fun getViewModel(): Class<LoginViewModel> = LoginViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressBar.visible(false)
        binding.btnLogin.enable(false)
        binding.edtPassword.addTextChangedListener {
            val email = binding.edtUsername.text.toString().trim()
            binding.btnLogin.enable(email.isNotEmpty() && it.toString().isNotEmpty())
        }
        binding.btnLogin.setOnClickListener {
            login()
        }
        binding.loginVM = viewModel
        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            binding.progressBar.visible(it is Resource.Loading)
            when (it) {
                is Resource.Success -> {
                    lifecycleScope.launch {
                        viewModel.saveAuthToken(it.value.token!!)
                        requireActivity().startNewActivity(HomeActivity::class.java)
                        Log.d("LOGGING", " Resource.Success: ${it.value.token}")
                    }
                }
                is Resource.Failure -> handleApiError(it) { login() }
            }
        })

        viewModel.message.observe(requireActivity(), Observer {
            it.getContentIfNotHandled()?.let { message ->
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                Log.d("LOGGING", message)
            }
        })
    }

    private fun login() {
        viewModel.onLogin()

    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLoginBinding = FragmentLoginBinding.inflate(inflater)

    override fun getFragmentRepository(): AuthRepository =
        AuthRepository(remoteDataSource.buildApi(AuthApi::class.java), userPreferences)

}