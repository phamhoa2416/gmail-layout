package com.example.gmail_layout.listOfMail

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gmail_layout.R
import com.bumptech.glide.Glide
import com.example.gmail_layout.data.Constant

class MailAdapter(diffCallback: DiffUtil.ItemCallback<MailItem>) : ListAdapter<MailItem, MailAdapter.MailBaseViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MailBaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            Constant.MAIL_CATEGORY_TYPE -> CategoryViewHolder(inflater.inflate(R.layout.item_category, parent, false))
            Constant.MAIL_ITEM_TYPE -> MailViewHolder(inflater.inflate(R.layout.item_mail, parent, false))
            else -> MailViewHolder(inflater.inflate(R.layout.item_mail, parent, false))
        }
    }

    override fun onBindViewHolder(holder: MailBaseViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).type
    }

    abstract class MailBaseViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        abstract fun bindData(item: MailItem)
    }

    // Category item ViewHolder
    inner class CategoryViewHolder(itemView: View): MailBaseViewHolder(itemView) {
        private val textViewTitle: TextView = itemView.findViewById(R.id.item_category_title)
        private val textViewDescription: TextView = itemView.findViewById(R.id.item_category_description)
        private val textViewCounter: TextView = itemView.findViewById(R.id.item_category_counter)
        private val imageCategory: ImageView = itemView.findViewById(R.id.item_category_image)

        override fun bindData(item: MailItem) {
            item.categoryItem?.let {
                textViewTitle.text = it.title
                textViewDescription.text = it.desc
                textViewCounter.text = it.content
                Glide.with(imageCategory.context).load(it.icon).into(imageCategory)

                val colorResource = when (it.color) {
                    "GREEN" -> R.color.green
                    "BLACK" -> R.color.black
                    "YELLOW" -> R.color.yellow
                    "BLUE" -> R.color.blue
                    "PURPLE" -> R.color.purple
                    else -> R.color.red
                }
                imageCategory.setColorFilter(imageCategory.context.getColor(colorResource))
                DrawableCompat.setTint(textViewCounter.background,imageCategory.context.getColor(colorResource))
            }
        }
    }

    // Mail item ViewHolder
    inner class MailViewHolder(itemView: View): MailBaseViewHolder(itemView) {
        private val textViewTitle: TextView = itemView.findViewById(R.id.item_mail_title)
        private val textViewContent: TextView = itemView.findViewById(R.id.item_mail_content)
        private val textViewDescription: TextView = itemView.findViewById(R.id.item_mail_description)
        private val textViewDate: TextView = itemView.findViewById(R.id.item_mail_date)
        private val imageUser: ImageView = itemView.findViewById(R.id.item_mail_image)
        private val imageFavorite: ImageView = itemView.findViewById(R.id.item_mail_favorite)

        override fun bindData(item: MailItem) {
            item.simpleItem?.let {
                setSelected(it.isRead)
                setFavorite(it.isFavorite)
                textViewTitle.text = it.title
                textViewDescription.text = it.description
                textViewContent.text = it.content
                Glide.with(imageUser.context).load(it.imgUrl).circleCrop().into(imageUser)
            }
        }

        private fun setSelected(isRead: Boolean) {
            val typeface = if (isRead) Typeface.DEFAULT else Typeface.DEFAULT_BOLD
            textViewTitle.typeface = typeface
            textViewDescription.typeface = typeface
            textViewDate.typeface = typeface
        }

        private fun setFavorite(favorite: Boolean) {
            if (favorite) {
                imageFavorite.setImageResource(R.drawable.ic_baseline_star_24)
                imageFavorite.setColorFilter(imageFavorite.context.getColor(R.color.yellow))
            } else {
                imageFavorite.setImageResource(R.drawable.ic_baseline_star_border_24)
                imageFavorite.setColorFilter(imageFavorite.context.getColor(R.color.light_text_second_color))
            }
        }
    }
}