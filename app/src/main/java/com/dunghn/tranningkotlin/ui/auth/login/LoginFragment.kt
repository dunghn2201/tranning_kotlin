package com.dunghn.tranningkotlin.ui.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dunghn.tranningkotlin.R
import com.dunghn.tranningkotlin.data.network.AuthApi
import com.dunghn.tranningkotlin.data.network.Resource
import com.dunghn.tranningkotlin.data.repository.AuthRepository
import com.dunghn.tranningkotlin.databinding.FragmentLoginBinding
import com.dunghn.tranningkotlin.ui.base.BaseFragment
import com.dunghn.tranningkotlin.ui.home.HomeActivity
import com.dunghn.tranningkotlin.util.*
import kotlinx.coroutines.launch


class LoginFragment :
    BaseFragment<LoginViewModel, FragmentLoginBinding, AuthRepository>() {
    private val SUCCESS_CODE: Int = 1

    override fun getViewModel(): Class<LoginViewModel> = LoginViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginVM = viewModel
        binding.progressBar.visible(false)
        binding.btnLogin.enable(false)
        binding.edtPassword.addTextChangedListener {
            val email = binding.edtUsername.text.toString().trim()
            binding.btnLogin.enable(email.isNotEmpty() && it.toString().isNotEmpty())
        }
        binding.btnLogin.setOnClickListener {
            login()
        }
        binding.tvGoSignIn.setOnClickListener {
            findNavController().navigate(R.id.registerFragment)
        }
        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            binding.progressBar.visible(it is Resource.Loading)
            when (it) {
                is Resource.Success -> {
                    if (it.value.success == SUCCESS_CODE) {
                        lifecycleScope.launch {
                            viewModel.saveAuthToken(it.value.token!!)
                            requireActivity().startNewActivity(HomeActivity::class.java)
                        }
                    } else {
                        requireView().snackbar(it.value.message)
                    }

                }
                is Resource.Failure -> handleApiError(it) {
                    hideKeyboard()
                    login()
                }
            }
        })

        viewModel.message.observe(requireActivity(), {
            it.getContentIfNotHandled()?.let { message ->
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
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
