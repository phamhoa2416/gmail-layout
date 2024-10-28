package com.example.gmail_layout.search

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build.*
import android.os.Bundle
import android.view.View
import android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.ListView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.gmail_layout.R

class SearchActivity : AppCompatActivity() {
    private lateinit var editSearch: EditText
    private lateinit var closeImg: ImageView
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        val selectedTheme = intent.getIntExtra("ThemeID", R.style.AppTheme)
        setTheme(selectedTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        if (selectedTheme == R.style.AppTheme) {
            setLightStatusBar(this)
        }

        setEditSearchFocus()
        setBackClickListener()
        setupSimpleSearchList()
        setupBackNavigation()
    }

    private fun setupBackNavigation() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
            }
        })
    }

    private fun setupSimpleSearchList() {
        listView = findViewById(R.id.list_view_search)
        listView.divider = null

        val lst = listOf("Google Mail", "Anna Steve", "Microsoft Store")
        val adapter = ArrayAdapter(
            this,
            R.layout.item_simple_search,
            R.id.search_text_view_show,
            lst
        )
        listView.adapter = adapter
    }

    private fun setBackClickListener() {
        closeImg = findViewById(R.id.menu_icon)
        closeImg.setOnClickListener {
            closeKeyboard()
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun openKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        editSearch.requestFocus()
        imm.showSoftInput(editSearch, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun closeKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val currentFocus = currentFocus
        currentFocus?.let {
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }

    companion object {
        @JvmStatic
        fun setLightStatusBar(activity: Activity) {
            when {
                VERSION.SDK_INT >= VERSION_CODES.R -> {
                    activity.window.decorView.windowInsetsController?.setSystemBarsAppearance(
                        APPEARANCE_LIGHT_STATUS_BARS,
                        APPEARANCE_LIGHT_STATUS_BARS
                    )
                }

                else -> {
                    @Suppress("DEPRECATION")
                    activity.window.decorView.systemUiVisibility =
                        activity.window.decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                }
            }
            activity.window.statusBarColor = Color.WHITE
        }
    }

    private fun setEditSearchFocus() {
        editSearch = findViewById(R.id.edit_search)
    }
}