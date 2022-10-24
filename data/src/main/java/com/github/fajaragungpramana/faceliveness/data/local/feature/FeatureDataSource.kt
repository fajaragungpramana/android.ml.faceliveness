package com.github.fajaragungpramana.faceliveness.data.local.feature

import com.github.fajaragungpramana.faceliveness.data.local.feature.entity.FeatureEntity
import javax.inject.Inject

class FeatureDataSource @Inject constructor() : IFeatureDataSource {

    override suspend fun getListFeature(): List<FeatureEntity> = arrayListOf(
        FeatureEntity(id = 1, title = "Liveness"),
        FeatureEntity(id = 2, title = "Face Recognation"),
        FeatureEntity(id = 3, title = "Fingerprint")
    )

}