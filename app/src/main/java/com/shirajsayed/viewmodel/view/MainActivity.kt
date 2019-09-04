package com.shirajsayed.viewmodel.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.shirajsayed.viewmodel.model.Item
import com.shirajsayed.viewmodel.viewmodel.MainActivityViewModel
import com.shirajsayed.viewmodel.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var item: String? = null
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var adapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this)
            .get(MainActivityViewModel::class.java)

        val observer = Observer<MutableList<Item>> {
            adapter.updateItems()
        }
        viewModel.getItems().observe(this, observer)

        adapter = ItemAdapter(viewModel.getItems().value ?: mutableListOf())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        buttonAdd.setOnClickListener {
            item = editText.text.toString()
            item?.let { itemIt ->
                viewModel.addItem(Item(itemIt))
                editText.text.clear()
            }
        }
    }
}
