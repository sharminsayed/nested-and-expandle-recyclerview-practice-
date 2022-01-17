package com.practice.nestedrecyclerview

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.RecyclerView


class ChildItemAdapter  // Constructor
internal constructor(private val context:Context,private val ChildItemList: List<ChildItem>) :
    RecyclerView.Adapter<ChildItemAdapter.ChildViewHolder>() {
    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        i: Int
    ): ChildViewHolder {

        // Here we inflate the corresponding
        // layout of the child item
        val view: View = LayoutInflater
            .from(viewGroup.context)
            .inflate(
                R.layout.child_item,
                viewGroup, false
            )
        return ChildViewHolder(view)
    }

    override fun onBindViewHolder(
        childViewHolder: ChildViewHolder,
        position: Int
    ) {

        // Create an instance of the ChildItem
        // class for the given position
        val childItem = ChildItemList[position]

        // For the created instance, set title.
        // No need to set the image for
        // the ImageViews because we have
        // provided the source for the images
        // in the layout file itself
        childViewHolder.delete.setOnClickListener {
            childViewHolder.parent!!.removeView(it.parent as View)
        }
        childViewHolder.add.setOnClickListener {
            val inflater =
                it.context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val rowView: View = inflater.inflate(R.layout.feild, null)
            childViewHolder.parent!!.addView(rowView, childViewHolder.parent!!.childCount - 1)
        }
        childViewHolder.card.setOnClickListener {
            Toast.makeText(context, "card click", Toast.LENGTH_SHORT).show()

        }
    }

    override fun getItemCount(): Int {

        // This method returns the number
        // of items we have added
        // in the ChildItemList
        // i.e. the number of instances
        // of the ChildItemList
        // that have been created
        return ChildItemList.size
    }

    // This class is to initialize
    // the Views present
    // in the child RecyclerView
    inner class ChildViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var parent: LinearLayout
        var card:CardView
        var delete:Button
        var add:Button

        init {
            parent = itemView.findViewById(
                R.id.parenet_layout
            )
            card=itemView.findViewById(R.id.card)
            delete=itemView.findViewById(R.id.onDelete)
            add=itemView.findViewById(R.id.add_feild)
        }
    }
}
