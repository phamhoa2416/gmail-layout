package com.example.gmail_layout.listOfMail

import androidx.recyclerview.widget.DiffUtil.ItemCallback

class MailCallback: ItemCallback<MailItem>() {
    override fun areItemsTheSame(oldItem: MailItem, newItem: MailItem): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: MailItem, newItem: MailItem): Boolean {
        return false
    }
}