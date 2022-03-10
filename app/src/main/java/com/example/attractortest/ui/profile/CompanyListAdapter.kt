package com.example.attractortest.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.attractortest.data.model.Company
import com.example.attractortest.databinding.ItemRecyclerviewBinding

class CompanyListAdapter : ListAdapter<Company, CompanyViewHolder>(
    Company.DIFF_UTIL_CALLBACK
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
        return CompanyViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class CompanyViewHolder(private val binding: ItemRecyclerviewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Company) {
        binding.tvCompanyName.text = item.name
        binding.tvCompanyPosition.text = item.position
    }

    companion object {
        fun create(parent: ViewGroup) = CompanyViewHolder(
            ItemRecyclerviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }
}