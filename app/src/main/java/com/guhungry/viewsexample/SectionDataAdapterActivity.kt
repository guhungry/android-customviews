package com.guhungry.viewsexample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.guhungry.viewsexample.adapters.SectionRecyclerAdapter
import kotlinx.android.synthetic.main.activity_load_more_adapter.*

class SectionDataAdapterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load_more_adapter)
    }

    override fun onStart() {
        super.onStart()

        setupList()
    }

    private fun setupList() {
        list_example.layoutManager = LinearLayoutManager(this)
        list_example.adapter = SectionRecyclerAdapter(this)
    }
}