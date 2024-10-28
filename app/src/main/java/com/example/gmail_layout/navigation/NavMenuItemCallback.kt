package com.example.gmail_layout.navigation

import androidx.recyclerview.widget.DiffUtil.ItemCallback

class NavMenuItemCallback: ItemCallback<NavItem>() {
    override fun areItemsTheSame(oldItem: NavItem, newItem: NavItem): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: NavItem, newItem: NavItem): Boolean {
        return false
    }
}