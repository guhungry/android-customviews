package com.guhungry.viewsexample.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.guhungry.views.recyclerview.BaseSectionedDataAdapter
import com.guhungry.views.recyclerview.BindableViewHolder
import com.guhungry.viewsexample.R
import kotlinx.android.synthetic.main.list_item_example.view.*

class SectionRecyclerAdapter(context: Context) : BaseSectionedDataAdapter<Int>() {
    private val inflater = LayoutInflater.from(context)

    init {
        generateSectionData((1..24))
    }

    override fun toSectionHeader(item: Int) = (((item - 1) / 3) + 1).toString()

    override fun onCreateHeaderViewHolder(parent: ViewGroup): BindableViewHolder<String> {
        val view = inflater.inflate(R.layout.list_item_example, parent, false)
        return HeaderViewHolder(view)
    }

    override fun onCreateItemViewHolder(parent: ViewGroup): BindableViewHolder<Int> {
        val view = inflater.inflate(R.layout.list_item_example, parent, false)
        return ItemViewHolder(view)
    }

    class HeaderViewHolder(itemView: View) : BindableViewHolder<String>(itemView) {
        init {
            itemView.setBackgroundResource(android.R.color.holo_green_light)
        }

        override fun bindData(data: String) {
            itemView.text1.text = data
        }

        override fun onRecycled() = Unit
    }


    class ItemViewHolder(itemView: View) : BindableViewHolder<Int>(itemView) {
        init {
            itemView.setBackgroundResource(android.R.color.holo_blue_bright)
        }

        override fun bindData(data: Int) {
            itemView.text1.text = data.toString()
        }

        override fun onRecycled() = Unit
    }
}
