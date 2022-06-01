package com.example.dimsumdetection.ui.recipe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dimsumdetection.R;
import com.example.dimsumdetection.database.PostgreSQL;
import com.example.dimsumdetection.databinding.FragmentRecipeBinding;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class RecipeFragment extends Fragment {
    private RecipeViewModel recipeiewModel;
    private FragmentRecipeBinding binding;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecipeRecyclerViewAdapter recyclerViewAdapter;

    private ArrayList<DimSum> dimsumList;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        recipeiewModel = new ViewModelProvider(this).get(RecipeViewModel.class);
        binding = FragmentRecipeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@Nullable View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);

        try {
            PostgreSQL postgreSQL = new PostgreSQL();
            Thread thread = new Thread(postgreSQL.SelectAll());
            thread.start();
            TimeUnit.SECONDS.sleep(1);
            dimsumList = postgreSQL.GetDimSums();

            recyclerViewAdapter = new RecipeRecyclerViewAdapter(getContext(), dimsumList);

            recyclerView.setAdapter(recyclerViewAdapter);
            recyclerView.setHasFixedSize(true);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
