package com.dunghn.tranningkotlin.ui.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.dunghn.tranningkotlin.R
import com.dunghn.tranningkotlin.databinding.FragmentLoginBinding
import com.dunghn.tranningkotlin.ui.base.BaseFragment


class LoginFragment : BaseFragment<LoginViewModel, FragmentLoginBinding>(R.layout.fragment_login) {


    override fun getViewModel(): Class<LoginViewModel> = LoginViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginVM = viewModel
        viewModel.message.observe(requireActivity(), Observer {
            it.getContentIfNotHandled()?.let { message ->
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        })
    }

}