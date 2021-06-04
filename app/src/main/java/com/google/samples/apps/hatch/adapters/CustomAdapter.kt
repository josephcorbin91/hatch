package com.google.samples.apps.hatch.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.samples.apps.hatch.data.Profile
import com.google.samples.apps.hatch.data.ProfileListItem
import com.google.samples.apps.hatch.data.ProfileHeader
import com.google.samples.apps.hatch.databinding.ChatHeaderViewBinding
import com.google.samples.apps.hatch.databinding.ChatRowViewBinding

/**
 * Adapter for the [RecyclerView] in [ProfileListFragment].
 */
class CustomAdapter : ListAdapter<ProfileListItem, RecyclerView.ViewHolder>(ProfileDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_PROFILE_ROW -> {
                ProfileViewHolder(ChatRowViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
            TYPE_PROFILE_HEADER -> {
                HeaderViewHolder(ChatHeaderViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is ProfileHeader -> TYPE_PROFILE_HEADER
            is Profile -> TYPE_PROFILE_ROW
            else -> throw IllegalArgumentException("Invalid Item Class")
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = getItem(position)
        when(holder.itemViewType) {
            TYPE_PROFILE_ROW -> {
                if (currentItem is Profile && holder is ProfileViewHolder) {
                    holder.bind(currentItem)
                }
            }
            TYPE_PROFILE_HEADER -> {
                if (currentItem is ProfileHeader && holder is HeaderViewHolder) {
                    holder.bind(currentItem)
                }
            }
            else -> throw IllegalArgumentException("Invalid Item Class")
        }
    }


    class ProfileViewHolder(
        private val binding: ChatRowViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Profile) {
            binding.apply {
                profile = item
                executePendingBindings()
            }
        }
    }

    class HeaderViewHolder(
        private val binding: ChatHeaderViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ProfileHeader) {
            binding.apply {
                profileHeader = item
                executePendingBindings()
            }
        }
    }

    companion object {
        const val TYPE_PROFILE_ROW = 0
        const val TYPE_PROFILE_HEADER = 1
    }
}

private class ProfileDiffCallback : DiffUtil.ItemCallback<ProfileListItem>() {

    override fun areItemsTheSame(oldItem: ProfileListItem, newItem: ProfileListItem): Boolean {
        return oldItem is ProfileHeader && newItem is ProfileHeader && oldItem.headerText == newItem.headerText || oldItem is Profile && newItem is Profile && oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ProfileListItem, newItem: ProfileListItem): Boolean {
        return if (oldItem is ProfileHeader && newItem is ProfileHeader) {
            oldItem.headerText == newItem.headerText
        } else if (oldItem is Profile && newItem is Profile) {
            oldItem.id == newItem.id && oldItem.avatarImage == newItem.avatarImage && oldItem.lastMessageSent == newItem.lastMessageSent
        } else {
            false
        }
    }
}
