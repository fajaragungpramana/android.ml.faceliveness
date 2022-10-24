package com.github.fajaragungpramana.faceliveness.data.local.feature

import com.github.fajaragungpramana.faceliveness.data.app.AppResult
import com.github.fajaragungpramana.faceliveness.data.extension.connection
import com.github.fajaragungpramana.faceliveness.data.extension.flowIO
import com.github.fajaragungpramana.faceliveness.data.local.feature.model.Feature
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FeatureRepository @Inject constructor(private val mFeatureDataSource: IFeatureDataSource) :
    IFeatureRepository {

    override suspend fun getListFeature(): Flow<AppResult<List<Feature>>> = flow {
        emit(connection { AppResult.OnSuccess(Feature.mapFromEntityListToList(mFeatureDataSource.getListFeature())) })
    }.flowIO()

}