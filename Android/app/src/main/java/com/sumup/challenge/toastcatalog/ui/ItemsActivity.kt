package com.sumup.challenge.toastcatalog.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sumup.challenge.toastcatalog.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ItemsActivity : ComponentActivity() {

    private lateinit var adapter: ItemsAdapter
    private val viewModel by viewModels<ItemViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items)

        initRecyclerView()
        setObservers()
    }

    private fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        val divider = DividerItemDecoration(this@ItemsActivity, DividerItemDecoration.VERTICAL)
        ContextCompat.getDrawable(this@ItemsActivity, R.drawable.divider)?.let {
            divider.setDrawable(it)
        }
        recyclerView.addItemDecoration(divider)
        adapter = ItemsAdapter()
        recyclerView.adapter = adapter
    }

    private fun setObservers() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.items.collect { items ->
                    adapter.submitList(items)
                }
            }
        }
    }
}
