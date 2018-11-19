package com.example.pbode.brflickr.views;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;

import com.example.pbode.brflickr.communcationlayer.DataTransferLayer;
import com.example.pbode.brflickr.communcationlayer.ImageDetailsTransfer;
import com.example.pbode.brflickr.databinding.ImageDetailsItemBinding;
import com.example.pbode.brflickr.viewmodels.ImageDetailsViewModel;

public class SearchResultViewHolder extends RecyclerView.ViewHolder {
    private ImageDetailsItemBinding binding;

    public SearchResultViewHolder(ImageDetailsItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
        binding.imageListItem.setOnClickListener(v -> showFullImage());
    }

    public void bind(ImageDetailsViewModel imageDetailsViewModel) {
        binding.setViewModel(imageDetailsViewModel);
        binding.executePendingBindings();
    }

    private void showFullImage() {
        Context context = binding.getRoot().getContext();
        Intent intent = new Intent(context, FullImageActivity.class);
        DataTransferLayer.addData(SearchResultViewHolder.class, new ImageDetailsTransfer(binding.getViewModel()));
        context.startActivity(intent);
    }
}
