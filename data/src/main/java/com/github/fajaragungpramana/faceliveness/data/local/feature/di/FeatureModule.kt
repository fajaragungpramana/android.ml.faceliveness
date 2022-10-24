package com.github.fajaragungpramana.faceliveness.data.local.feature.di

import com.github.fajaragungpramana.faceliveness.data.domain.feature.FeatureInteractor
import com.github.fajaragungpramana.faceliveness.data.domain.feature.FeatureUseCase
import com.github.fajaragungpramana.faceliveness.data.local.feature.FeatureDataSource
import com.github.fajaragungpramana.faceliveness.data.local.feature.FeatureRepository
import com.github.fajaragungpramana.faceliveness.data.local.feature.IFeatureDataSource
import com.github.fajaragungpramana.faceliveness.data.local.feature.IFeatureRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object FeatureModule {

    @Provides
    fun provideDataSource(): IFeatureDataSource = FeatureDataSource()

    @Provides
    fun provideRepository(featureDataSource: FeatureDataSource): IFeatureRepository =
        FeatureRepository(featureDataSource)

    @Provides
    fun provideUseCase(featureRepository: FeatureRepository): FeatureUseCase =
        FeatureInteractor(featureRepository)

}