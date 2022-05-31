package com.example.dimsumdetection.ui.recipe;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dimsumdetection.R;

public class RecipeViewModel extends ViewModel{
    private MutableLiveData<String> mText;

    public RecipeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is recipe fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}


