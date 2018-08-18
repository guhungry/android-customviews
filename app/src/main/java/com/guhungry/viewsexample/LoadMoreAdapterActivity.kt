package com.guhungry.viewsexample

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.guhungry.views.recyclerview.BindableViewHolder
import com.guhungry.views.recyclerview.LoadMoreAdapter
import com.guhungry.views.recyclerview.OnLoadMoreListener
import com.guhungry.viewsexample.LoadMoreAdapterActivity.ExampleRecyclerAdapter.ExampleViewHolder
import kotlinx.android.synthetic.main.activity_load_more_adapter.*
import kotlinx.android.synthetic.main.list_item_example.view.*

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

    class ExampleRecyclerAdapter(context: Context) : LoadMoreAdapter<ExampleViewHolder>() {
        private val inflater = LayoutInflater.from(context)
        private var items = 10

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
}