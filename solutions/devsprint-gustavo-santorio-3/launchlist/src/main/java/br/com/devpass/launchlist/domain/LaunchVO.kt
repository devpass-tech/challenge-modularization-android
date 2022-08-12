package br.com.devpass.launchlist.domain

import androidx.recyclerview.widget.DiffUtil

data class LaunchVO(
    val name: String,
    val number: String,
    var date: String,
    val status: String,
    val image: String
    ) {
    companion object : DiffUtil.ItemCallback<LaunchVO>() {
        override fun areItemsTheSame(oldItem: LaunchVO, newItem: LaunchVO): Boolean {
            return oldItem.number == newItem.number
        }

        override fun areContentsTheSame(oldItem: LaunchVO, newItem: LaunchVO): Boolean {
            return oldItem == newItem
        }
    }
}