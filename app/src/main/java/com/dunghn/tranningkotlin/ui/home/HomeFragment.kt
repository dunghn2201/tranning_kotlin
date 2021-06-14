package com.dunghn.tranningkotlin.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.dunghn.tranningkotlin.data.network.Resource
import com.dunghn.tranningkotlin.data.network.UserApi
import com.dunghn.tranningkotlin.data.repository.UserRepository
import com.dunghn.tranningkotlin.data.response.User
import com.dunghn.tranningkotlin.databinding.FragmentHomeBinding
import com.dunghn.tranningkotlin.ui.base.BaseFragment
import com.dunghn.tranningkotlin.util.visible
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding, UserRepository>() {
    override fun getViewModel(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater)

    override fun getFragmentRepository(): UserRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataSource.buildApi(UserApi::class.java, token)
        return UserRepository(api)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressBar2.visible(false)
        viewModel.getUser()
        viewModel.user.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    binding.progressBar2.visible(false)

                    updateUI(it.value.result)
                }
                is Resource.Loading -> {
                    binding.progressBar2.visible(true)

                }
            }
        })
        binding.btnLogout.setOnClickListener {
            logout()
        }
    }

    private fun updateUI(user: User) {
        with(binding) {
            tvName.text = user.username
        }
    }

}