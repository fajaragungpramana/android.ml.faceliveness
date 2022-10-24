package com.github.fajaragungpramana.faceliveness.ui.state

import com.github.fajaragungpramana.faceliveness.data.local.feature.model.Feature

sealed class FeatureState {

    data class FeatureLoading(val isLoading: Boolean) : FeatureState()
    data class FeatureSuccess(val listFeature: List<Feature>?) : FeatureState()
    data class FeatureFailure(val message: String?) : FeatureState()

}