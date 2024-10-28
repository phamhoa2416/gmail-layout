package com.example.gmail_layout.listOfMail

data class MailSimpleItem (
    var id: Int = 0,
    var title: String = "",
    var content: String = "",
    var description: String = "",
    var sentEmail: String = "",
    var sentUsername: String = "",
    var imgUrl: Int = 0,
    var isFavorite: Boolean = false,
    var isRead: Boolean = false
) {
    constructor(
        id: Int,
        title: String,
        content: String,
        description: String,
        sentEmail: String,
        sentUsername: String,
        imgUrl: Int
    ) : this(id, title, content, description, sentEmail, sentUsername, imgUrl, isFavorite = false, isRead = false)

    constructor(
        id: Int,
        title: String,
        content: String,
        description: String,
        sentEmail: String,
        sentUsername: String,
        imgUrl: Int,
        isRead: Boolean
    ) : this(id, title, content, description, sentEmail, sentUsername, imgUrl, isFavorite = false, isRead)

    constructor(
        id: Int,
        title: String,
        description: String,
        imgUrl: Int,
        content: String
    ) : this(id, title, content, description, sentEmail = "", sentUsername = "", imgUrl)

    constructor(
        id: Int,
        title: String,
        description: String,
        imgUrl: Int,
        content: String,
        isFavorite: Boolean
    ) : this(id, title, content, description, sentEmail = "", sentUsername = "", imgUrl, isFavorite = isFavorite)

    constructor(
        id: Int,
        title: String,
        description: String,
        imgUrl: Int,
        content: String,
        isRead: Boolean,
        isFavorite: Boolean
    ) : this(id, title, content, description, sentEmail = "", sentUsername = "", imgUrl, isFavorite, isRead)

}