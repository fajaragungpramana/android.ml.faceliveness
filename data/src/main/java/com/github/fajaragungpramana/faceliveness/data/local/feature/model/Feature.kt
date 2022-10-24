package com.github.fajaragungpramana.faceliveness.data.local.feature.model

import androidx.recyclerview.widget.DiffUtil
import com.github.fajaragungpramana.faceliveness.data.local.feature.entity.FeatureEntity

data class Feature(
    val id: Int? = null,
    val title: String? = null
) {

    companion object {

        val diffUtil = object : DiffUtil.ItemCallback<Feature>() {

            override fun areItemsTheSame(oldItem: Feature, newItem: Feature) = oldItem == newItem

            override fun areContentsTheSame(oldItem: Feature, newItem: Feature) =
                oldItem.id == newItem.id

        }

        fun mapFromEntityListToList(list: List<FeatureEntity>): List<Feature> {
            val data = arrayListOf<Feature>()
            list.map {
                data.add(
                    Feature(id = it.id, title = it.title)
                )
            }
            return data
        }

    }

}
