package com.example.pbode.brflickr.views;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;

import com.example.pbode.brflickr.R;
import com.example.pbode.brflickr.databinding.ActivityImageSearchBinding;
import com.example.pbode.brflickr.viewmodels.ImageSearchViewModel;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class ImageSearchActivity extends AppCompatActivity {

    @Inject
    ImageSearchViewModel imageSearchViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        ActivityImageSearchBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_image_search);
        binding.setViewModel(imageSearchViewModel);
        binding.photoListView.setLayoutManager(new LinearLayoutManager(this));
        binding.photoListView.setAdapter(imageSearchViewModel.getAdapter());
        setSupportActionBar(binding.toolbar);

        SearchView searchView = findViewById(R.id.search_bar);
        searchView.setOnClickListener(v -> searchView.setIconified(false));
    }
}
