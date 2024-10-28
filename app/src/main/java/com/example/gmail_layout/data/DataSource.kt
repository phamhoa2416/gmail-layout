package com.example.gmail_layout.data

import com.example.gmail_layout.R
import com.example.gmail_layout.listOfMail.MailCategoryItem
import com.example.gmail_layout.listOfMail.MailItem
import com.example.gmail_layout.listOfMail.MailSimpleItem

object DataSource {
    // Dummy Titles
    private const val TITLE_1 = "Anna Smith"
    private const val TITLE_2 = "Adobe Creative Cloud Updates"
    private const val TITLE_3 = "JoHn Doe"
    private const val TITLE_4 = "Kelsey Green"
    private const val TITLE_5 = "Space News Latest Update"
    private const val TITLE_6 = "Anna Smith"
    private const val TITLE_7 = "Android Blog Daily Post"

    // Dummy User Images
    private val IMG_1 = R.drawable.pnggoogle
    private val IMG_2 = R.drawable.adobe
    private val IMG_3 = R.drawable.user4
    private val IMG_4 = R.drawable.user
    private val IMG_5 = R.drawable.user2
    private val IMG_6 = R.drawable.girl0
    private val IMG_7 = R.drawable.androidstudio

    // Dummy Mail Content
    private const val Content = "This is the app layout demo"

    // Dummy Description
    private const val DESC_1 = "Lor" +
            "em ipsum dolor sit amet"

    fun getListMail(): List<MailItem> = mutableListOf<MailItem>().apply {
        // Category Items
        add(MailItem(
            MailCategoryItem(
                R.drawable.ic_outline_info_24,
                "Updates",
                "Google, GOG.COM, Up labs And 19 more...\nCheck Important Recent update",
                "",
                "YELLOW",
                12
            )
        ))

        add(
            MailItem(
            MailCategoryItem(
                R.drawable.ic_outline_local_offer_24,
                "PROMOTION",
                DESC_1,
                "GREEN",
                "GREEN",
                122
            )
        )
        )

        add(MailItem(
            MailCategoryItem(
                R.drawable.ic_outline_forum_24,
                TITLE_1,
                DESC_1,
                "PURPLE",
                "PURPLE",
                5
            )
        ))

        // Simple Mail Items
        add(MailItem(
            MailSimpleItem(
                1,
                TITLE_1,
                DESC_1,
                IMG_1,
                Content,
                isFavorite = true
            )
        ))

        add(MailItem(
            MailSimpleItem(
                2,
                TITLE_2,
                DESC_1,
                R.drawable.usernoimg01,
                Content
            )
        ))

        add(MailItem(
            MailSimpleItem(
                3,
                TITLE_3,
                DESC_1,
                R.drawable.usernoimg0,
                Content,
                isFavorite = true,
                isRead = false
            )
        ))

        add(MailItem(
            MailSimpleItem(
                4,
                TITLE_4,
                DESC_1,
                IMG_4,
                Content
            )
        ))

        add(MailItem(
            MailSimpleItem(
                5,
                TITLE_5,
                DESC_1,
                IMG_5,
                Content
            )
        ))

        add(MailItem(
            MailSimpleItem(
                6,
                TITLE_6,
                DESC_1,
                IMG_6,
                Content
            )
        ))

        add(MailItem(
            MailSimpleItem(
                7,
                TITLE_7,
                DESC_1,
                IMG_7,
                Content,
                isFavorite = true
            )
        ))

        add(MailItem(
            MailSimpleItem(
                8,
                TITLE_6,
                DESC_1,
                IMG_6,
                Content,
                isFavorite = true
            )
        ))

        add(MailItem(
            MailSimpleItem(
                9,
                TITLE_7,
                DESC_1,
                R.drawable.usernoimg01,
                Content
            )
        ))

        add(MailItem(
            MailSimpleItem(
                10,
                TITLE_6,
                DESC_1,
                IMG_6,
                Content
            )
        ))

        add(MailItem(
            MailSimpleItem(
                11,
                TITLE_7,
                DESC_1,
                IMG_1,
                Content
            )
        ))

        add(MailItem(
            MailSimpleItem(
                12,
                TITLE_6,
                DESC_1,
                R.drawable.usernoimg0,
                Content,
                isFavorite = true
            )
        ))

        add(MailItem(
            MailSimpleItem(
                13,
                TITLE_7,
                DESC_1,
                IMG_5,
                Content
            )
        ))
    }
}