package com.example.pbode.brflickr;

import android.support.v7.widget.RecyclerView;

import com.example.pbode.brflickr.databinding.ImageDetailsItemBinding;

public class SearchResultViewHolder extends RecyclerView.ViewHolder {
    private ImageDetailsItemBinding binding;

    public SearchResultViewHolder(ImageDetailsItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(ImageDetailsViewModel imageDetailsViewModel) {
        binding.setViewModel(imageDetailsViewModel);
        binding.executePendingBindings();
    }
}
