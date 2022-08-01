package com.devpass.spaceapp.launchlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.devpass.spaceapp.databinding.ListItemBinding
import com.devpass.spaceapp.launchlist.domain.LaunchVO

class LaunchListAdapter : ListAdapter<LaunchVO, LaunchViewHolder>(LaunchVO) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchViewHolder {
        return LaunchViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: LaunchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class LaunchViewHolder(binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    private val imageLaunch = binding.ivLogo
    private val numberLaunch = binding.tvNumber
    private val nameLaunch = binding.tvName
    private val dateLaunch = binding.tvDate
    private val statusLaunch = binding.tvStatus

    fun bind(model: LaunchVO) {
//        imageLaunch.setImageResource(model.image)
        numberLaunch.text = model.number
        nameLaunch.text = model.name
        dateLaunch.text = model.date
        statusLaunch.text = model.status
    }

    companion object {
        fun from(parent: ViewGroup): LaunchViewHolder {
            return LaunchViewHolder(
                ListItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }
    }
}