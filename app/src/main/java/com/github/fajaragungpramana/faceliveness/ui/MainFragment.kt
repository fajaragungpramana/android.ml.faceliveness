package com.github.fajaragungpramana.faceliveness.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
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
import com.google.android.material.snackbar.Snackbar
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

        val cameraPermission =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) {
                if (it) {
                    val action = MainFragmentDirections.actionMainFragmentToLivenessFragment()
                    findNavController().navigate(action)
                } else {
                    Snackbar
                        .make(
                            viewBinding.rvFeature,
                            "Need access camera",
                            Snackbar.LENGTH_INDEFINITE
                        )
                        .setAction("Okay") { openApplicationSettings() }
                        .show()
                }
            }

        mFeatureAdapter = FeatureAdapter {
            when (it.id) {
                FeatureEntity.ID.Liveness -> {
                    if (ContextCompat.checkSelfPermission(
                            requireActivity(),
                            Manifest.permission.CAMERA
                        ) == PackageManager.PERMISSION_DENIED
                    ) cameraPermission.launch(Manifest.permission.CAMERA)
                    else {
                        val action = MainFragmentDirections.actionMainFragmentToLivenessFragment()
                        findNavController().navigate(action)
                    }
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

    private fun openApplicationSettings() {
        requireActivity().startActivity(Intent().apply {
            action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            data = Uri.fromParts(
                "package",
                requireActivity().packageName,
                null
            )
        })
    }

}