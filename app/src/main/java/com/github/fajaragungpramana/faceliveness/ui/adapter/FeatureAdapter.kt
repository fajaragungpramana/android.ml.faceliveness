package com.github.fajaragungpramana.faceliveness.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.fajaragungpramana.faceliveness.common.app.AppRecyclerViewAdapter
import com.github.fajaragungpramana.faceliveness.common.app.AppRecyclerViewHolder
import com.github.fajaragungpramana.faceliveness.data.local.feature.model.Feature
import com.github.fajaragungpramana.faceliveness.databinding.AdapterFeatureBinding

class FeatureAdapter(private val mItemClickListener: (Feature) -> Unit) :
    AppRecyclerViewAdapter<AdapterFeatureBinding, Feature, FeatureAdapter.ViewHolder>(Feature.diffUtil) {

    override fun onViewBinding(viewGroup: ViewGroup) =
        AdapterFeatureBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

    override fun onViewHolder(view: View) = ViewHolder(view)

    inner class ViewHolder(view: View) : AppRecyclerViewHolder<Feature>(view) {

        override fun bindItem(item: Feature, position: Int) {
            viewBinding.mtvTitle.text = item.title

            viewBinding.root.setOnClickListener { mItemClickListener.invoke(item) }
        }

    }

}