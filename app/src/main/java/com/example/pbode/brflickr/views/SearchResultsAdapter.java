package com.example.pbode.brflickr.views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.pbode.brflickr.databinding.ImageDetailsItemBinding;
import com.example.pbode.brflickr.service.ImageDetails;
import com.example.pbode.brflickr.viewmodels.ImageDetailsViewModel;
import com.example.pbode.brflickr.viewmodels.ImageSearchViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class SearchResultsAdapter extends RecyclerView.Adapter<SearchResultViewHolder> {

    private List<ImageDetails> imageList = new ArrayList<>();
    private ImageDetailsViewModel.Factory imageDetailsViewModelFactory;
    private ImageSearchViewModel imageSearchViewModel;

    @Inject
    public SearchResultsAdapter(ImageDetailsViewModel.Factory imageDetailsViewModelFactory) {
        this.imageDetailsViewModelFactory = imageDetailsViewModelFactory;
    }

    @NonNull
    @Override
    public SearchResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ImageDetailsItemBinding binding = ImageDetailsItemBinding.inflate(layoutInflater, parent, false);
        return new SearchResultViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(SearchResultViewHolder holder, int position) {
        holder.bind(imageDetailsViewModelFactory.newInstance(imageList.get(position)));
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public void setPhotoList(List<ImageDetails> imageDetailsList) {
        this.imageList = imageDetailsList;
    }

    public void setImageSearchViewModel(ImageSearchViewModel imageSearchViewModel) {
        this.imageSearchViewModel = imageSearchViewModel;
    }
}
