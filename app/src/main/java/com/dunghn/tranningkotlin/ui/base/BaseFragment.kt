package com.dunghn.tranningkotlin.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.dunghn.tranningkotlin.data.UserPreferences
import com.dunghn.tranningkotlin.data.network.RemoteDataSource
import com.dunghn.tranningkotlin.data.network.UserApi
import com.dunghn.tranningkotlin.data.repository.BaseRepository
import com.dunghn.tranningkotlin.ui.auth.AuthActivity
import com.dunghn.tranningkotlin.util.startNewActivity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


abstract class BaseFragment<VM : BaseViewModel, B : ViewDataBinding, R : BaseRepository> :
    Fragment() {
    protected lateinit var userPreferences: UserPreferences
    protected lateinit var binding: B
    protected lateinit var viewModel: VM
    protected val remoteDataSource = RemoteDataSource()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userPreferences = UserPreferences(requireContext())
        binding = getFragmentBinding(inflater, container)
        val factory = ViewModelFactory(getFragmentRepository())
        viewModel = ViewModelProvider(this, factory).get(getViewModel())
        lifecycleScope.launch {
            userPreferences.authToken.first()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = this@BaseFragment
        }
    }

    fun logout() = lifecycleScope.launch {
        val authToken = userPreferences.authToken.first()
        val api = remoteDataSource.buildApi(UserApi::class.java, authToken)
        viewModel.logout(api)
        userPreferences.clear()
        requireActivity().startNewActivity(AuthActivity::class.java)
    }

    abstract fun getViewModel(): Class<VM>

    abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): B
    abstract fun getFragmentRepository(): R
}