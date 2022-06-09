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
import com.example.dimsumdetection.database.DataHandler;
import com.example.dimsumdetection.database.PostgreSQL;
import com.example.dimsumdetection.database.SavePreference;
import com.example.dimsumdetection.databinding.FragmentRecipeBinding;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class DimSumFragment extends Fragment {
    private DimSumViewModel recipeiewModel;
    private FragmentRecipeBinding binding;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private DimSumRecyclerViewAdapter recyclerViewAdapter;

    private String tag;
    private boolean isSearch = false;

    private ArrayList<DimSum> dimsumList;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        recipeiewModel = new ViewModelProvider(this).get(DimSumViewModel.class);
        binding = FragmentRecipeBinding.inflate(inflater, container, false);

        tag = (getArguments() != null) ? getArguments().getString("TAG") : "";
        isSearch = !tag.isEmpty() && !tag.equals("");

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@Nullable View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);

        try {
            PostgreSQL postgreSQL = new PostgreSQL(getContext());
            if(!isSearch) {
                Thread getAll = new Thread(postgreSQL.SelectAllDimSum());
                getAll.start();
            }else{
                Thread getOne = new Thread(postgreSQL.SelectDimSum(tag));
                getOne.start();
            }
            TimeUnit.SECONDS.sleep(1);
            dimsumList = postgreSQL.GetDimSums();


        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        recyclerViewAdapter = new DimSumRecyclerViewAdapter(getContext(), dimsumList);

        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setHasFixedSize(true);
    }

}
