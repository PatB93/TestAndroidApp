package com.example.pbode.brflickr.views;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.pbode.brflickr.R;
import com.example.pbode.brflickr.communcationlayer.DataTransferLayer;
import com.example.pbode.brflickr.communcationlayer.ImageDetailsTransfer;
import com.example.pbode.brflickr.databinding.ActivityFullImageBinding;

public class FullImageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageDetailsTransfer imageDetailsTransfer = (ImageDetailsTransfer) DataTransferLayer.getData(SearchResultViewHolder.class);
        ActivityFullImageBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_full_image);
        binding.setViewModel(imageDetailsTransfer.getImageDetailsViewModel());
    }
}
