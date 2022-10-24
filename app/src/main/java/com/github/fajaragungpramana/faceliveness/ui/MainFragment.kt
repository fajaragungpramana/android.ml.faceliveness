package com.github.fajaragungpramana.faceliveness.ui

import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.github.fajaragungpramana.faceliveness.common.app.AppFragment
import com.github.fajaragungpramana.faceliveness.common.contract.AppObserver
import com.github.fajaragungpramana.faceliveness.databinding.FragmentMainBinding
import com.github.fajaragungpramana.faceliveness.ui.state.FeatureState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainFragment : AppFragment<FragmentMainBinding>(), AppObserver {

    private val mViewModel: MainViewModel by viewModels()

    override fun onViewBinding(container: ViewGroup?) =
        FragmentMainBinding.inflate(layoutInflater, container, false)

    override fun onCreated(savedInstanceState: Bundle?) {
        mViewModel.getListFeature()
    }

    override fun onStateObserver() {
        lifecycleScope.launchWhenCreated {
            mViewModel.featureState.collectLatest {

                when (it) {
                    is FeatureState.FeatureLoading -> {
                    }
                    is FeatureState.FeatureSuccess -> {
                    }
                    is FeatureState.FeatureFailure -> {
                    }
                }

            }
        }
    }

}