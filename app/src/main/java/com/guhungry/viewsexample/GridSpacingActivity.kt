package com.guhungry.viewsexample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.guhungry.views.recyclerview.decoration.GridSpacingItemDecoration
import com.guhungry.viewsexample.adapters.ExampleRecyclerAdapter
import kotlinx.android.synthetic.main.activity_spacing_item_decorator.*

class GridSpacingActivity : AppCompatActivity() {
    private lateinit var adapter: ExampleRecyclerAdapter
    private lateinit var decorator: GridSpacingItemDecoration

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
        list_example.layoutManager = GridLayoutManager(this, NUMBER_OF_COLUMNS)

        decorator = GridSpacingItemDecoration(NUMBER_OF_COLUMNS, 40, switch_edge.isChecked)
        list_example.addItemDecoration(decorator)

        adapter = ExampleRecyclerAdapter(this, 50)
        list_example.adapter = adapter
    }

    companion object {
        private const val NUMBER_OF_COLUMNS = 2
    }
}