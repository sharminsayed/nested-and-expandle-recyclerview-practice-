package com.practice.nestedrecyclerview


class ParentItem(
    // Getter and Setter methods
    // for each parameter
    // Declaration of the variables
    var parentItemTitle: String,
    var expand : Boolean = false,
    ChildItemList: List<ChildItem>
) {
    private var ChildItemList: List<ChildItem>
    fun getChildItemList(): List<ChildItem> {
        return ChildItemList
    }

    fun setChildItemList(
        childItemList: List<ChildItem>
    ) {
        ChildItemList = childItemList
    }

    // Constructor of the class
    // to initialize the variables
    init {
        this.ChildItemList = ChildItemList
    }
}
