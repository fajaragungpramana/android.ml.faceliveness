package com.github.fajaragungpramana.faceliveness.ui

import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.github.fajaragungpramana.faceliveness.common.app.AppFragment
import com.github.fajaragungpramana.faceliveness.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : AppFragment<FragmentMainBinding>() {

    private val mViewModel: MainViewModel by viewModels()

    override fun onViewBinding(container: ViewGroup?) =
        FragmentMainBinding.inflate(layoutInflater, container, false)

    override fun onCreated(savedInstanceState: Bundle?) {

    }

}