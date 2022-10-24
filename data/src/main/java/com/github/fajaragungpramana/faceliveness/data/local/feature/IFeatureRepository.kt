package com.github.fajaragungpramana.faceliveness.data.local.feature

import com.github.fajaragungpramana.faceliveness.data.app.AppResult
import com.github.fajaragungpramana.faceliveness.data.local.feature.model.Feature
import kotlinx.coroutines.flow.Flow

interface IFeatureRepository {

    suspend fun getListFeature(): AppResult<Flow<List<Feature>>>

}