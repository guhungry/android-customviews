package com.guhungry.viewsexample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.guhungry.views.recyclerview.OnLoadMoreListener
import com.guhungry.viewsexample.adapters.ExampleRecyclerAdapter
import kotlinx.android.synthetic.main.activity_load_more_adapter.*

class LoadMoreAdapterActivity : AppCompatActivity() {
    private lateinit var adapter: ExampleRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load_more_adapter)
    }

    override fun onStart() {
        super.onStart()

        setupList()
    }

    private fun setupList() {
        adapter = ExampleRecyclerAdapter(this).apply {
            setOnLoadMoreListener(OnLoadMoreListener { loadMoreData() })
        }
        list_example.layoutManager = LinearLayoutManager(this)
        list_example.adapter = adapter
    }

    private fun loadMoreData() {
        setLoading(true)
        list_example.postDelayed({
            adapter.addData()
            setLoading(false)
        }, 3000)
    }

    private fun setLoading(loading: Boolean) {
        adapter.isLoading = loading
        progressBar.visibility = if (loading) View.VISIBLE else View.GONE
    }
}