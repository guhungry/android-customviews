package com.guhungry.viewsexample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.guhungry.views.recyclerview.decoration.LinearSpacingItemDecoration
import com.guhungry.viewsexample.adapters.ExampleRecyclerAdapter
import kotlinx.android.synthetic.main.activity_spacing_item_decorator.*

class VerticalLinearSpacingActivity : AppCompatActivity() {
    private lateinit var adapter: ExampleRecyclerAdapter
    private lateinit var decorator: LinearSpacingItemDecoration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spacing_item_decorator)
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
        list_example.layoutManager = LinearLayoutManager(this, ORIENTATION, false)
        decorator = LinearSpacingItemDecoration(ORIENTATION, 40, switch_edge.isChecked)
        list_example.addItemDecoration(decorator)

        adapter = ExampleRecyclerAdapter(this, 50)
        list_example.adapter = adapter
    }

    companion object {
        private const val ORIENTATION = LinearLayoutManager.VERTICAL
    }
}