package com.example.gmail_layout.navigation

import com.example.gmail_layout.data.Constant

class NavItem {
    var type: Int
    var menuItem: MenuItem? = null
    var labelItem: LabelItem? = null

    constructor(menuItem: MenuItem) {
        this.menuItem = menuItem
        this.type = Constant.NAV_MENU_TYPE
    }

    constructor(labelItem: LabelItem) {
        this.labelItem = labelItem
        this.type = Constant.NAV_TEXT_TYPE
    }
}