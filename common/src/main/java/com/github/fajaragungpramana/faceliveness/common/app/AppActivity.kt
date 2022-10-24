package com.github.fajaragungpramana.faceliveness.common.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.github.fajaragungpramana.faceliveness.common.contract.AppObserver

abstract class AppActivity<VB : ViewBinding> : AppCompatActivity() {

    private lateinit var mViewBinding: VB
    protected val viewBinding: VB
        get() = mViewBinding

    protected abstract fun onViewBinding(): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding = onViewBinding()
        setContentView(viewBinding.root)

        if (this is AppObserver) onStateObserver()
    }

}