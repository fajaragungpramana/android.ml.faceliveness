package com.github.fajaragungpramana.faceliveness.ui

import android.os.Bundle
import com.github.fajaragungpramana.faceliveness.common.app.AppActivity
import com.github.fajaragungpramana.faceliveness.databinding.ActivityMainBinding

class MainActivity : AppActivity<ActivityMainBinding>() {

    override fun onViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreated(savedInstanceState: Bundle?) {

    }

}