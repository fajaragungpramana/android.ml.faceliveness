package com.github.fajaragungpramana.faceliveness.data.domain.feature

import com.github.fajaragungpramana.faceliveness.data.app.AppResult
import com.github.fajaragungpramana.faceliveness.data.local.feature.IFeatureRepository
import com.github.fajaragungpramana.faceliveness.data.local.feature.model.Feature
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FeatureInteractor @Inject constructor(private val mFeatureRepository: IFeatureRepository) :
    FeatureUseCase {

    override suspend fun getListFeature(): Flow<AppResult<List<Feature>>> =
        mFeatureRepository.getListFeature()

}