package com.shirajsayed.viewmodel.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shirajsayed.viewmodel.model.Item

class MainActivityViewModel : ViewModel() {

    private val items = MutableLiveData<MutableList<Item>>()

    init {
        items.value = mutableListOf()
    }

    fun addItem(item: Item) {
        val tmpItems = items.value
        tmpItems?.add(item)
        items.value = tmpItems

        Log.v("CONSOLE", "items ${items.value?.size}")
    }

    fun getItems(): LiveData<MutableList<Item>> {
        return items
    }
}