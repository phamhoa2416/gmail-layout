package com.example.gmail_layout.navigation

data class MenuItem(
    var icon: Int,
    var title: String,
    var isSelected: Boolean = false,
    var numNotification: Int = 0
) {
    var drawable: Int
        get() = icon
        set(value) { icon = value}
}