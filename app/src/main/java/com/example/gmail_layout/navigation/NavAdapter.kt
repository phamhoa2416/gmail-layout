package com.example.gmail_layout.navigation

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gmail_layout.R
import com.example.gmail_layout.data.Constant

class NavAdapter(diffCallback: DiffUtil.ItemCallback<NavItem>) :
    ListAdapter<NavItem, NavAdapter.NavBaseViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavBaseViewHolder {
        return when (viewType) {
            Constant.NAV_MENU_TYPE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_nav_menu, parent, false)
                MenuViewHolder(view)
            }
            Constant.NAV_TEXT_TYPE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_nav_label, parent, false)
                LabelViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_nav_label, parent, false)
                LabelViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: NavBaseViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).type
    }

    abstract class NavBaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bindData(item: NavItem)
    }

    inner class LabelViewHolder(itemView: View) : NavBaseViewHolder(itemView) {
        private val labelTextView: TextView = itemView.findViewById(R.id.nav_label)

        override fun bindData(item: NavItem) {
            item.labelItem?.let { labelItem ->
                labelTextView.text = labelItem.label
            }
        }
    }

    inner class MenuViewHolder(itemView: View) : NavBaseViewHolder(itemView) {
        private val menuTitle: TextView = itemView.findViewById(R.id.nav_title)
        private val number: TextView = itemView.findViewById(R.id.nav_number)
        private val icon: ImageView = itemView.findViewById(R.id.icon)
        private val container: ConstraintLayout = itemView.findViewById(R.id.nav_item_container)

        @SuppressLint("SetTextI18n")
        override fun bindData(item: NavItem) {
            item.menuItem?.let { menuItem ->
                menuTitle.text = menuItem.title
                icon.setImageResource(menuItem.icon)

                when {
                    menuItem.numNotification > 99 -> {
                        number.visibility = View.VISIBLE
                        number.text = "+99"
                    }
                    menuItem.numNotification != 0 -> {
                        number.visibility = View.VISIBLE
                        number.text = menuItem.numNotification.toString()
                    }
                    else -> number.visibility = View.GONE
                }

                setSelected(menuItem.isSelected)
            }
        }

        private fun setSelected(isSelected: Boolean) {
            container.background = if (isSelected) {
                AppCompatResources.getDrawable(container.context, R.drawable.nav_select_bg)
            } else {
                ColorDrawable(Color.TRANSPARENT)
            }
        }
    }
}