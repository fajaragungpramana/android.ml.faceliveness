package com.github.fajaragungpramana.faceliveness.ui

import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.fajaragungpramana.faceliveness.common.app.AppFragment
import com.github.fajaragungpramana.faceliveness.common.contract.AppObserver
import com.github.fajaragungpramana.faceliveness.data.local.feature.entity.FeatureEntity
import com.github.fajaragungpramana.faceliveness.databinding.FragmentMainBinding
import com.github.fajaragungpramana.faceliveness.ui.adapter.FeatureAdapter
import com.github.fajaragungpramana.faceliveness.ui.state.FeatureState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainFragment : AppFragment<FragmentMainBinding>(), AppObserver {

    private val mViewModel: MainViewModel by viewModels()

    private lateinit var mFeatureAdapter: FeatureAdapter

    override fun onViewBinding(container: ViewGroup?) =
        FragmentMainBinding.inflate(layoutInflater, container, false)

    override fun onCreated(savedInstanceState: Bundle?) {
        mViewModel.getListFeature()

        mFeatureAdapter = FeatureAdapter {
            when (it.id) {
                FeatureEntity.ID.Liveness -> {
                    val action = MainFragmentDirections.actionMainFragmentToLivenessFragment()
                    findNavController().navigate(action)
                }

                FeatureEntity.ID.FaceRecognition -> {

                }

                FeatureEntity.ID.Fingerprint -> {

                }
                else -> {}
            }
        }

        viewBinding.apply {
            rvFeature.layoutManager = LinearLayoutManager(requireActivity())
            rvFeature.adapter = mFeatureAdapter
        }
    }

    override fun onStateObserver() {
        lifecycleScope.launchWhenCreated {
            mViewModel.featureState.collectLatest {

                when (it) {
                    is FeatureState.FeatureLoading -> {
                    }
                    is FeatureState.FeatureSuccess -> {
                        mFeatureAdapter.submitList(it.listFeature)
                    }
                    is FeatureState.FeatureFailure -> {
                    }
                }

            }
        }
    }

}