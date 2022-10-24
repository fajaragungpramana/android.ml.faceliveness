package com.github.fajaragungpramana.faceliveness.ui

import android.os.Bundle
import android.view.ViewGroup
import com.github.fajaragungpramana.faceliveness.common.app.AppFragment
import com.github.fajaragungpramana.faceliveness.databinding.FragmentMainBinding

class MainFragment : AppFragment<FragmentMainBinding>() {

    override fun onViewBinding(container: ViewGroup?) =
        FragmentMainBinding.inflate(layoutInflater, container, false)

    override fun onCreated(savedInstanceState: Bundle?) {

    }

}