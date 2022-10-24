package com.github.fajaragungpramana.faceliveness.data.local.feature

import com.github.fajaragungpramana.faceliveness.data.local.feature.entity.FeatureEntity

interface IFeatureDataSource {

    suspend fun getListFeature(): List<FeatureEntity>

}