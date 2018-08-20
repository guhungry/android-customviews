package com.guhungry.viewsexample

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.guhungry.views.recyclerview.BindableViewHolder
import com.guhungry.views.recyclerview.listener.OnItemClickListener
import com.guhungry.viewsexample.MainActivity.ExampleAdapter.ItemViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_item_example.view.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupList()
    }

    private val examples = listOf(
            ExampleActivityModel(R.string.activity_load_more_adapter, LoadMoreAdapterActivity::class.java),
            ExampleActivityModel(R.string.activity_grid_spacing_decorator, GridSpacingActivity::class.java),
            ExampleActivityModel(R.string.activity_horizontal_linear_spacing_decorator, HorizontalLinearSpacingActivity::class.java),
            ExampleActivityModel(R.string.activity_vertical_linear_spacing_decorator, VerticalLinearSpacingActivity::class.java)
    )

    class ExampleActivityModel(val name: Int, val activity: Class<*>)

    private fun setupList() {
        list.layoutManager = GridLayoutManager(this, 2)
        list.adapter = ExampleAdapter(examples).apply {
            setOnItemClickListener(OnItemClickListener { startActivity(Intent(this@MainActivity, it)) })
        }
    }

    inner class ExampleAdapter(private val list: List<ExampleActivityModel>) : RecyclerView.Adapter<ItemViewHolder>() {
        private var onItemClickListener: OnItemClickListener<Class<*>>? = null

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            return ItemViewHolder(layoutInflater.inflate(R.layout.list_item_example, parent, false))
        }

        override fun getItemCount() = list.size

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            holder.bindData(list[position])
        }

        fun setOnItemClickListener(listener: OnItemClickListener<Class<*>>) {
            onItemClickListener = listener
        }

        inner class ItemViewHolder(view: View) : BindableViewHolder<ExampleActivityModel>(view) {
            init {
                itemView.setOnClickListener { onItemClickListener?.onItemClick(itemView.tag as Class<*>) }
            }

            override fun bindData(data: ExampleActivityModel) {
                itemView.tag = data.activity

                itemView.text1.setText(data.name)
            }

            override fun onRecycled() = Unit
        }
    }
}