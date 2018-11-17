package com.example.pbode.brflickr

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import com.example.pbode.brflickr.databinding.ActivityImageSearchBinding
import kotlinx.android.synthetic.main.activity_image_search.*

class ImageSearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityImageSearchBinding>(this, R.layout.activity_image_search)
        binding.viewModel = ImageSearchViewModel()
        binding.photoListView.layoutManager = LinearLayoutManager(this)
        setSupportActionBar(toolbar)

        val searchBar = findViewById<SearchView>(R.id.search_bar)
        searchBar.setOnClickListener {
            search_bar.isIconified = false
        }
    }
}
