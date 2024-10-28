package com.example.gmail_layout

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.view.View
import android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gmail_layout.data.DataSource
import com.example.gmail_layout.listOfMail.*
import com.example.gmail_layout.navigation.*
import com.example.gmail_layout.search.SearchActivity
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var mailRecyclerView: RecyclerView
    private lateinit var navRecyclerView: RecyclerView
    private lateinit var mailAdapter: MailAdapter
    private lateinit var extFab: ExtendedFloatingActionButton
    private lateinit var editSearch: TextView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var iconDrawerLayout: ImageView
    private lateinit var navAdapter: NavAdapter
    private var selectedThemeID: Int = R.style.DarkTheme

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)
            setTheme(selectedThemeID)
            enableEdgeToEdge()
            setContentView(R.layout.activity_main)

            if (selectedThemeID == R.style.AppTheme) {
                setLightStatusBar(this)
            }

            initView()
            setupFabBehavior()
            initNavigationMenu()
            swipeRightToRemove()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initView() {
        try {
            extFab = findViewById(R.id.extFab100)
            iconDrawerLayout = findViewById(R.id.menu_icon)
            mailRecyclerView = findViewById(R.id.recycler_view_mail)

            mailAdapter = MailAdapter(MailCallback())

            mailRecyclerView.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = mailAdapter
            }

            DataSource.getListMail().let { mailList ->
                mailAdapter.submitList(mailList)
            }

            editSearch = findViewById<TextView>(R.id.edit_search).apply {
                isFocusable = false
                setOnClickListener {
                    try {
                        Intent(this@MainActivity, SearchActivity::class.java).apply {
                            putExtra("ThemeID", selectedThemeID)
                            action = Intent.ACTION_SEARCH
                            startActivity(this)
                        }
                    } catch (e: Exception) {
                        Toast.makeText(this@MainActivity, "Error opening search: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupFabBehavior() {
        try {
            mailRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (::extFab.isInitialized) {  // Check if extFab is initialized
                        if (dy > 0) {
                            extFab.shrink()
                        } else {
                            extFab.extend()
                        }
                    }
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun initNavigationMenu() {
        try {
            drawerLayout = findViewById(R.id.drawer_layout)
            navRecyclerView = findViewById(R.id.navigation_recycler_view)

            iconDrawerLayout.setOnClickListener {
                if (::drawerLayout.isInitialized) {
                    drawerLayout.open()
                }
            }

            navRecyclerView.layoutManager = LinearLayoutManager(this)
            val navigationMenu = createNavigationMenu()

            navAdapter = NavAdapter(NavMenuItemCallback())
            navAdapter.submitList(navigationMenu)
            navRecyclerView.adapter = navAdapter
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Error initializing navigation: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun createNavigationMenu(): List<NavItem> {
        return mutableListOf<NavItem>().apply {
            MenuItem(R.drawable.ic_baseline_all_inbox_24,
                R.string.all_inboxes.toString(), false, 120)

            MenuItem(R.drawable.ic_baseline_inbox_24,
                R.string.main.toString(), false, 120)

            MenuItem(R.drawable.ic_outline_local_offer_24,
                R.string.promotions.toString(), false, 20)

            MenuItem(R.drawable.ic_baseline_person_outline_24,
                R.string.social.toString(), false, 120)

            MenuItem(R.drawable.ic_outline_info_24,
                R.string.update.toString(), false, 5)

            LabelItem(R.string.all_labels.toString())

            MenuItem(R.drawable.ic_baseline_star_border_24,
                R.string.favorite.toString(), false)

            MenuItem(R.drawable.ic_outline_send_24,
                R.string.sent.toString(), false)

            MenuItem(R.drawable.ic_baseline_snooze_24,
                R.string.scheduled.toString(),false)

            MenuItem(R.drawable.ic_outline_drafts_24,
                R.string.drafts.toString(), false)

            MenuItem(R.drawable.ic_baseline_mail_outline_24,
                R.string.all_mails.toString(), false)

            MenuItem(R.drawable.ic_outline_info_24,
                R.string.spam.toString(), false)

            MenuItem(R.drawable.ic_baseline_delete_outline_24,
                R.string.trash.toString(), false)

            MenuItem(R.drawable.ic_outline_label_24,
                R.string.label.toString(), false)

            LabelItem(R.string.google_apps.toString())

            MenuItem(R.drawable.ic_baseline_calendar_today_24,
                R.string.calendar.toString(), false)

            MenuItem(R.drawable.ic_baseline_person_outline_24,
                R.string.contacts.toString(), false)

            MenuItem(R.drawable.ic_outline_settings_24,
                R.string.settings.toString(), false)
        }
    }

    private fun swipeRightToRemove() {
        try {
            val simpleItemTouchCallback = object : ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.RIGHT
            ) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean = false

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    try {
                        val position = viewHolder.adapterPosition
                        mailAdapter.notifyItemRemoved(position)
                        Toast.makeText(this@MainActivity, "Item removed", Toast.LENGTH_SHORT).show()
                    } catch (e: Exception) {
                        e.printStackTrace()
                        Toast.makeText(this@MainActivity, "Error removing item", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            ItemTouchHelper(simpleItemTouchCallback).attachToRecyclerView(mailRecyclerView)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    companion object {
        @JvmStatic
        fun setLightStatusBar(activity: Activity) {
            try {
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
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}