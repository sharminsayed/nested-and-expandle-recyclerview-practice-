package com.practice.nestedrecyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.RecycledViewPool


class ParentItemAdapter internal constructor(private val context:Context,private val itemList: List<ParentItem>) :
    RecyclerView.Adapter<ParentItemAdapter.ParentViewHolder>() {
    // An object of RecyclerView.RecycledViewPool
    // is created to share the Views
    // between the child and
    // the parent RecyclerViews
    private val viewPool = RecycledViewPool()
    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        i: Int
    ): ParentViewHolder {

        // Here we inflate the corresponding
        // layout of the parent item
        val view: View = LayoutInflater
            .from(viewGroup.context)
            .inflate(
                R.layout.parent_item,
                viewGroup, false
            )
        return ParentViewHolder(view)
    }

    override fun onBindViewHolder(
        parentViewHolder: ParentViewHolder,
        position: Int
    ) {

        // Create an instance of the ParentItem
        // class for the given position
        val parentItem = itemList[position]

        // For the created instance,
        // get the title and set it
        // as the text for the TextView
        parentViewHolder.ParentItemTitle.text = parentItem.parentItemTitle
        parentViewHolder.expand.visibility = if (parentItem.expand) View.VISIBLE else View.GONE
        // on Click of the item take parent card view in our case
        // revert the boolean "expand"
        parentViewHolder.ParentItemTitle.setOnClickListener {
            parentItem.expand = !parentItem.expand
            notifyDataSetChanged()
        }

        // Create a layout manager
        // to assign a layout
        // to the RecyclerView.

        // Here we have assigned the layout
        // as LinearLayout with vertical orientation
        val layoutManager = LinearLayoutManager(
            parentViewHolder.ChildRecyclerView
                .context,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        // Since this is a nested layout, so
        // to define how many child items
        // should be prefetched when the
        // child RecyclerView is nested
        // inside the parent RecyclerView,
        // we use the following method
        layoutManager.initialPrefetchItemCount = parentItem
            .getChildItemList()
            .size

        // Create an instance of the child
        // item view adapter and set its
        // adapter, layout manager and RecyclerViewPool
        val childItemAdapter = ChildItemAdapter(context,
            parentItem
                .getChildItemList()
        )
        parentViewHolder.ChildRecyclerView.layoutManager = layoutManager
        parentViewHolder.ChildRecyclerView.adapter = childItemAdapter
        parentViewHolder.ChildRecyclerView
            .setRecycledViewPool(viewPool)
    }

    // This method returns the number
    // of items we have added in the
    // ParentItemList i.e. the number
    // of instances we have created
    // of the ParentItemList
    override fun getItemCount(): Int {
        return itemList.size
    }

    // This class is to initialize
    // the Views present in
    // the parent RecyclerView
    inner class ParentViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val ParentItemTitle: TextView
        val ChildRecyclerView: RecyclerView
        val expand:LinearLayout

        init {
            ParentItemTitle = itemView
                .findViewById(
                    R.id.parent_item_title
                )
            ChildRecyclerView = itemView
                .findViewById(
                    R.id.child_recyclerview
                )

            expand = itemView
                .findViewById(
                    R.id.expand
                )
        }
    }
}
