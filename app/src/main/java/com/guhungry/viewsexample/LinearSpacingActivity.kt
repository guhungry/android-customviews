package com.guhungry.viewsexample

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.guhungry.views.recyclerview.BindableViewHolder
import com.guhungry.views.recyclerview.LoadMoreAdapter
import com.guhungry.views.recyclerview.decoration.LinearSpacingItemDecoration
import com.guhungry.viewsexample.LinearSpacingActivity.ExampleRecyclerAdapter.ExampleViewHolder
import kotlinx.android.synthetic.main.activity_linear_spacing.*
import kotlinx.android.synthetic.main.list_item_example.view.*

class LinearSpacingActivity : AppCompatActivity() {
    private lateinit var adapter: ExampleRecyclerAdapter
    private lateinit var decorator: LinearSpacingItemDecoration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_linear_spacing)
    }

    override fun onStart() {
        super.onStart()

        setupMode()
        setupList()
    }

    private fun setupMode() {
        switch_edge.setOnCheckedChangeListener { _, checked ->
            decorator.includeEdge = checked
            adapter.notifyDataSetChanged()
        }
    }

    private fun setupList() {
        list_example.layoutManager = LinearLayoutManager(this)

        decorator = LinearSpacingItemDecoration(LinearLayoutManager.VERTICAL, 40, switch_edge.isChecked)
        list_example.addItemDecoration(decorator)

        adapter = ExampleRecyclerAdapter(this)
        list_example.adapter = adapter
    }

    class ExampleRecyclerAdapter(context: Context) : LoadMoreAdapter<ExampleViewHolder>() {
        private val inflater = LayoutInflater.from(context)
        private var items = 50

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

        class ExampleViewHolder(private val view: View) : BindableViewHolder<Int>(view) {
            override fun bindData(data: Int) {
                view.text1.text = data.toString()
            }

            override fun onRecycled() = Unit
        }
    }
}