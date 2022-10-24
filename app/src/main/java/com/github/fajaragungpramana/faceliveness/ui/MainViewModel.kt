package com.github.fajaragungpramana.faceliveness.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.fajaragungpramana.faceliveness.data.domain.feature.FeatureUseCase
import com.github.fajaragungpramana.faceliveness.data.extension.onResultListener
import com.github.fajaragungpramana.faceliveness.ui.state.FeatureState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mFeatureUseCase: FeatureUseCase) : ViewModel(),
    IMainEvent {

    private val _featureState by lazy { Channel<FeatureState>() }
    val featureState: Flow<FeatureState>
        get() = _featureState.receiveAsFlow()

    override fun getListFeature(): Job = viewModelScope.launch {
        mFeatureUseCase.getListFeature().collectLatest { result ->
            result.onResultListener(
                onLoading = {
                    _featureState.send(FeatureState.FeatureLoading(isLoading = it))
                },
                onSuccess = {
                    _featureState.send(FeatureState.FeatureSuccess(it))
                },
                onFailure = { _, _ ->
                    _featureState.send(FeatureState.FeatureFailure(null))
                },
                onError = {
                    _featureState.send(FeatureState.FeatureFailure(it.message))
                }
            )
        }
    }

}