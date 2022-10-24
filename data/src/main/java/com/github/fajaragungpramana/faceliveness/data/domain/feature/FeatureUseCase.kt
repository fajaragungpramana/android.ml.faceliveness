package com.github.fajaragungpramana.faceliveness.data.domain.feature

import com.github.fajaragungpramana.faceliveness.data.app.AppResult
import com.github.fajaragungpramana.faceliveness.data.local.feature.model.Feature
import kotlinx.coroutines.flow.Flow

interface FeatureUseCase {

    suspend fun getListFeature(): Flow<AppResult<List<Feature>>>

}