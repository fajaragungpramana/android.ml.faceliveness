package com.github.fajaragungpramana.faceliveness.ui.liveness

import android.os.Bundle
import android.view.ViewGroup
import com.github.fajaragungpramana.faceliveness.common.app.AppFragment
import com.github.fajaragungpramana.faceliveness.databinding.FragmentLivenessBinding

class LivenessFragment : AppFragment<FragmentLivenessBinding>() {

    override fun onViewBinding(container: ViewGroup?) =
        FragmentLivenessBinding.inflate(layoutInflater, container, false)

    override fun onCreated(savedInstanceState: Bundle?) {

    }

}