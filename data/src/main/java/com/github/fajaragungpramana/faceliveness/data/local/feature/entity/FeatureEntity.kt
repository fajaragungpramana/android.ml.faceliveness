package com.github.fajaragungpramana.faceliveness.data.local.feature.entity

data class FeatureEntity(
    val id: ID? = null,
    val title: String? = null
) {
    enum class ID {
        Liveness, FaceRecognition, Fingerprint
    }
}