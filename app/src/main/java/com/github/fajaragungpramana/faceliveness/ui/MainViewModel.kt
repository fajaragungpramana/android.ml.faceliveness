package com.github.fajaragungpramana.faceliveness.ui

import androidx.lifecycle.ViewModel
import com.github.fajaragungpramana.faceliveness.data.domain.feature.FeatureUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mFeatureUseCase: FeatureUseCase) : ViewModel() {
}