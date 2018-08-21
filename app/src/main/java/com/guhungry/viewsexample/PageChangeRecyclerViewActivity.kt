package com.guhungry.viewsexample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.support.v7.widget.RecyclerView
import com.guhungry.views.recyclerview.addOnPageChangeListener
import com.guhungry.views.recyclerview.decoration.LinearSpacingItemDecoration
import com.guhungry.views.recyclerview.listener.OnPageChangeListener
import com.guhungry.viewsexample.adapters.ExampleRecyclerAdapter
import kotlinx.android.synthetic.main.activity_page_change_recycler_view.*

class PageChangeRecyclerViewActivity : AppCompatActivity() {
    private lateinit var adapter: ExampleRecyclerAdapter
    private lateinit var decorator: LinearSpacingItemDecoration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_change_recycler_view)
    }

    override fun onStart() {
        super.onStart()

        setupList()
    }

    private fun setupList() {
        list_example.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)

        list_example.addOnPageChangeListener(OnPageChangeListener { updatePageIndicator(it) })
        LinearSnapHelper().attachToRecyclerView(list_example)

        adapter = ExampleRecyclerAdapter(this, layout = R.layout.list_item_fullpage)
        list_example.adapter = adapter

        updatePageIndicator(0)
    }

    private fun updatePageIndicator(position: Int) {
        page_indicator.text = "${position + 1} / ${adapter.itemCount}"
    }
}