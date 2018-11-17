package com.example.pbode.brflickr;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.pbode.brflickr.databinding.ImageDetailsItemBinding;
import com.example.pbode.brflickr.service.ImageDetails;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsAdapter extends RecyclerView.Adapter<SearchResultViewHolder> {

    private List<ImageDetails> imageList = new ArrayList<>();

    @NonNull
    @Override
    public SearchResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ImageDetailsItemBinding binding = ImageDetailsItemBinding.inflate(layoutInflater, parent, false);
        return new SearchResultViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(SearchResultViewHolder holder, int position) {
        holder.bind(new ImageDetailsViewModel(imageList.get(position)));
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public void setPhotoList(List<ImageDetails> imageDetailsList) {
        this.imageList = imageDetailsList;
    }
}
