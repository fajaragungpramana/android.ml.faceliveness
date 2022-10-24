package com.github.fajaragungpramana.faceliveness.data.local.feature

import com.github.fajaragungpramana.faceliveness.data.local.feature.entity.FeatureEntity
import javax.inject.Inject

class FeatureDataSource @Inject constructor() : IFeatureDataSource {

    override suspend fun getListFeature(): List<FeatureEntity> = arrayListOf(
        FeatureEntity(id = FeatureEntity.ID.Liveness, title = "Liveness"),
        FeatureEntity(id = FeatureEntity.ID.FaceRecognition, title = "Face Recognition"),
        FeatureEntity(id = FeatureEntity.ID.Fingerprint, title = "Fingerprint")
    )

}