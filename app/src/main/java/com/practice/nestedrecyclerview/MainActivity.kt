package com.practice.nestedrecyclerview

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.skydoves.expandablelayout.ExpandableLayout
import com.skydoves.expandablelayout.expandableLayout
import androidx.recyclerview.widget.LinearLayoutManager


import androidx.recyclerview.widget.RecyclerView
import com.practice.nestedrecyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // view binding for the activity
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    lateinit var expnadbale:ExpandableLayout
    // get reference to the adapter class
    private var languageList = ArrayList<Language>()
    private var testList = ArrayList<ParentItem>()
    private lateinit var rvAdapter: RvAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleRecyclerView()
       // handleExpandleLayout()

    }

    fun handleExpandleLayout(){


        // define layout manager for the Recycler view
        binding.parentRecyclerview.layoutManager = LinearLayoutManager(this)

        // attach adapter to the recycler view
        rvAdapter = RvAdapter(languageList)
        binding.parentRecyclerview.adapter = rvAdapter

        // create new objects
        // add some row data
        val language1 = Language(
            "Java",
            "Java is an Object Oriented Programming language." +
                    " Java is used in all kind of applications like Mobile Applications (Android is Java based), " +
                    "desktop applications, web applications, client server applications, enterprise applications and many more. ",
            false
        )
        val language2 = Language(
            "Kotlin",
            "Kotlin is a statically typed, general-purpose programming language" +
                    " developed by JetBrains, that has built world-class IDEs like IntelliJ IDEA, PhpStorm, Appcode, etc.",
            false
        )
        val language3 = Language(
            "Python",
            "Python is a high-level, general-purpose and a very popular programming language." +
                    " Python programming language (latest Python 3) is being used in web development, Machine Learning applications, " +
                    "along with all cutting edge technology in Software Industry.",
            false
        )
        val language4 = Language(
            "CPP",
            "C++ is a general purpose programming language and widely used now a days for " +
                    "competitive programming. It has imperative, object-oriented and generic programming features. ",
            false
        )

        // add items to list
        languageList.add(language1)
        languageList.add(language2)
        languageList.add(language3)
        languageList.add(language4)

        rvAdapter.notifyDataSetChanged()


        rvAdapter.notifyDataSetChanged()
    }

    fun handleRecyclerView() {
        val ParentRecyclerViewItem = findViewById<RecyclerView>(
            R.id.parent_recyclerview
        )

        val layoutManager = LinearLayoutManager(
            this@MainActivity
        )

        val parentItemAdapter = ParentItemAdapter(this,
            ParentItemList()!!
        )

        // Set the layout manager
        // and adapter for items
        // of the parent recyclerview

        // Set the layout manager
        // and adapter for items
        // of the parent recyclerview
        ParentRecyclerViewItem
            .setAdapter(parentItemAdapter)
        ParentRecyclerViewItem
            .setLayoutManager(layoutManager)

    }

    private fun ParentItemList(): List<ParentItem>? {
        val itemList: MutableList<ParentItem> = ArrayList()
        val item = ParentItem(
            "Title 1",
            false,
            ChildItemList()!!
        )
        itemList.add(item)
        val item1 = ParentItem(
            "Title 2",
            false,
            ChildItemList()!!
        )
        itemList.add(item1)
        val item2 = ParentItem(
            "Title 3",
            false,
            ChildItemList()!!
        )
        itemList.add(item2)
        val item3 = ParentItem(
            "Title 4",
            false,
            ChildItemList()!!
        )
        itemList.add(item3)
        return itemList
    }

    // Method to pass
    private fun ChildItemList(): List<ChildItem>? {
        val ChildItemList: MutableList<ChildItem> = ArrayList()
        ChildItemList.add(ChildItem("Card 1"))

        return ChildItemList
    }
    }