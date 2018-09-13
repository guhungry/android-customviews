package com.guhungry.views.recyclerview

import android.support.annotation.VisibleForTesting
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

abstract class BaseSectionedDataAdapter<T> : RecyclerView.Adapter<BindableViewHolder<*>>() {
    ////////////////////////
    // Generate Section Data
    ////////////////////////
    private var sectionedData = mutableListOf<ListItem>()

    protected fun generateSectionData(data: Iterable<T>) {
        data.map(this::toSectionHeader)
                .distinct()
                .sorted()
                .forEach { section ->
                    sectionedData.add(ListItem(TYPE_SECTION_HEADER, section))
                    sectionedData.addAll(
                            data.filter { toSectionHeader(it) == section }
                                    .map { ListItem(TYPE_ITEM, it as Any) }
                    )
                }
        privateNotifyDataSetChanged()
    }

    abstract fun toSectionHeader(item: T): String

    @VisibleForTesting
    open fun privateNotifyDataSetChanged() = notifyDataSetChanged()

    /////////////////////
    // Create View Holder
    /////////////////////
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindableViewHolder<*> {
        return when (viewType) {
            TYPE_SECTION_HEADER -> onCreateHeaderViewHolder(parent)
            else -> onCreateItemViewHolder(parent)
        }
    }

    abstract fun onCreateHeaderViewHolder(parent: ViewGroup): BindableViewHolder<String>
    abstract fun onCreateItemViewHolder(parent: ViewGroup): BindableViewHolder<T>

    ///////////////////
    // Bind View Holder
    ///////////////////
    override fun onBindViewHolder(holder: BindableViewHolder<*>, position: Int) {
        val item = sectionedData[position]
        when (item.type) {
            TYPE_SECTION_HEADER -> doBindViewHolder(holder, item.data as String)
            else -> doBindViewHolder(holder, item.data as T)
        }
    }

    private fun <X> doBindViewHolder(holder: BindableViewHolder<*>, data: X) = (holder as? BindableViewHolder<X>)?.bindData(data)

    /////////////////
    // Item Functions
    /////////////////
    override fun getItemCount() = sectionedData.size

    override fun getItemViewType(position: Int) = sectionedData[position].type

    companion object {
        @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
        const val TYPE_SECTION_HEADER = 0
        @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
        const val TYPE_ITEM = 1
    }
}

private data class ListItem(val type: Int, val data: Any)