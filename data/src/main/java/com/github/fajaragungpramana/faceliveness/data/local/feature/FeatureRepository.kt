package com.github.fajaragungpramana.faceliveness.data.local.feature

import com.github.fajaragungpramana.faceliveness.data.app.AppResult
import com.github.fajaragungpramana.faceliveness.data.extension.connection
import com.github.fajaragungpramana.faceliveness.data.local.feature.model.Feature
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FeatureRepository @Inject constructor(private val mFeatureDataSource: IFeatureDataSource) :
    IFeatureRepository {

    override suspend fun getListFeature(): AppResult<Flow<List<Feature>>> = connection {
        AppResult.OnSuccess(
            flow { emit(Feature.mapFromEntityListToList(mFeatureDataSource.getListFeature())) }
                .flowOn(Dispatchers.IO)
        )
    }

}