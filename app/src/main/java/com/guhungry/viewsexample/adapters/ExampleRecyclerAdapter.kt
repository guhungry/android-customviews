package com.guhungry.viewsexample.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.guhungry.views.recyclerview.BaseLoadMoreAdapter
import com.guhungry.views.recyclerview.BindableViewHolder
import com.guhungry.viewsexample.R
import kotlinx.android.synthetic.main.list_item_example.view.*

class ExampleRecyclerAdapter(context: Context, initialItems: Int = 10) : BaseLoadMoreAdapter<ExampleRecyclerAdapter.ExampleViewHolder>() {
    private val inflater = LayoutInflater.from(context)
    private var items = initialItems

    override fun doBindViewHolder(holder: ExampleViewHolder, position: Int) {
        holder.bindData(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        return ExampleViewHolder(inflater.inflate(R.layout.list_item_example, parent, false))
    }

    override fun onViewRecycled(holder: ExampleViewHolder) {
        super.onViewRecycled(holder)

        holder.onRecycled()
    }

    override fun getItemCount(): Int = items

    fun addData() {
        items += 10
        notifyDataSetChanged()
    }

    class ExampleViewHolder(private val view: View) : BindableViewHolder<Int>(view) {
        override fun bindData(data: Int) {
            view.text1.text = data.toString()
        }

        override fun onRecycled() = Unit
    }
}