package com.example.dimsumdetection.ui.favorite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dimsumdetection.R;
import com.example.dimsumdetection.database.DataHandler;
import com.example.dimsumdetection.database.PostgreSQL;
import com.example.dimsumdetection.database.SavePreference;
import com.example.dimsumdetection.databinding.FragmentFavoriteBinding;
import com.example.dimsumdetection.databinding.FragmentRecipeBinding;
import com.example.dimsumdetection.ui.recipe.DimSum;
import com.example.dimsumdetection.ui.recipe.DimSumRecyclerViewAdapter;
import com.example.dimsumdetection.ui.recipe.DimSumViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FavoriteFragment extends Fragment {

    private FavoriteViewModel favoriteViewModel;
    private FragmentFavoriteBinding binding;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private FavoriteRecyclerViewAdapter favoriteRecyclerViewAdapter;

    private ArrayList<DimSum> dimsumList = new ArrayList<DimSum>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        favoriteViewModel = new ViewModelProvider(this).get(FavoriteViewModel.class);
        binding = FragmentFavoriteBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@Nullable View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(layoutManager);

        dimsumList = SavePreference.GetDimSumFavoriteList(getContext());

        favoriteRecyclerViewAdapter = new FavoriteRecyclerViewAdapter(getContext(), this, dimsumList);

        recyclerView.setAdapter(favoriteRecyclerViewAdapter);
        recyclerView.setHasFixedSize(true);
    }

    public void RefreshFragment() {
        dimsumList.clear();
        dimsumList.addAll(SavePreference.GetDimSumFavoriteList(getContext()));
        favoriteRecyclerViewAdapter.notifyDataSetChanged();
    }
}