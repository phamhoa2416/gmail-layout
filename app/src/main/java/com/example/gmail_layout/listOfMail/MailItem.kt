package com.example.gmail_layout.listOfMail

import com.example.gmail_layout.data.Constant

class MailItem {
    var categoryItem: MailCategoryItem? = null
    var simpleItem: MailSimpleItem? = null
    var type: Int

    constructor(categoryItem: MailCategoryItem) {
        this.categoryItem = categoryItem
        this.type = Constant.MAIL_CATEGORY_TYPE
    }

    constructor(simpleItem: MailSimpleItem) {
        this.simpleItem = simpleItem
        this.type = Constant.MAIL_ITEM_TYPE
    }
}