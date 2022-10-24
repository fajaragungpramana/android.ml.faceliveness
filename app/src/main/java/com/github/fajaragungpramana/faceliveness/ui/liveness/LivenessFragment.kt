package com.github.fajaragungpramana.faceliveness.ui.liveness

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import com.ekycsolutions.ekycid.Initializer
import com.ekycsolutions.ekycid.livenessdetection.*
import com.ekycsolutions.ekycid.models.FrameStatus
import com.github.fajaragungpramana.faceliveness.common.app.AppFragment
import com.github.fajaragungpramana.faceliveness.databinding.FragmentLivenessBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LivenessFragment : AppFragment<FragmentLivenessBinding>(), LivenessDetectionEventListener {

    private val mInitializer by lazy { Initializer(requireActivity()) }

    private lateinit var mLivenessDetectionCameraView: LivenessDetectionCameraView

    override fun onViewBinding(container: ViewGroup?) =
        FragmentLivenessBinding.inflate(layoutInflater, container, false)

    override fun onCreated(savedInstanceState: Bundle?) {

    }

    override fun onResume() {
        super.onResume()

        mInitializer.start {
            CoroutineScope(Dispatchers.Main).launch {
                mLivenessDetectionCameraView = viewBinding.ldcView
                mLivenessDetectionCameraView.addListener(this@LivenessFragment)
                mLivenessDetectionCameraView.setOptions(LivenessDetectionOptions(
                    arrayListOf(LivenessPromptType.LOOK_LEFT, LivenessPromptType.LOOK_RIGHT, LivenessPromptType.BLINKING)
                ))
                mLivenessDetectionCameraView.start()
            }
        }
    }

    override fun onInitialize() {
        Log.d("FFFF", "onInitialize")
    }

    override fun onFrame(frameStatus: FrameStatus) {
        if (frameStatus != FrameStatus.PROCESSING) {
            Log.d("FFFF", "onFrame: $frameStatus")
        }
    }

    override fun onPromptCompleted(completedPromptIndex: Int, success: Boolean, progress: Float) {
        Log.d("FFFF", "onPromptCompleted")
    }

    override fun onAllPromptsCompleted(detection: LivenessDetectionResult) {
        Log.d("FFFF", "onAllPromptsCompleted")
    }

    override fun onFocus() {
        Log.d("FFFF", "onFocus")
    }

    override fun onFocusDropped() {
        Log.d("FFFF", "onFocusDropped")
    }

    override fun onCountDownChanged(current: Int, max: Int) {
        Log.d("FFFF", "onCountDownChanged")
    }

}