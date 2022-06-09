package com.example.dimsumdetection.ui.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import com.example.dimsumdetection.R;
import com.example.dimsumdetection.database.PostgreSQL;
import com.example.dimsumdetection.databinding.FragmentHomeBinding;
import com.example.dimsumdetection.ml.Model;
import com.squareup.picasso.Picasso;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static androidx.navigation.fragment.NavHostFragment.findNavController;

public class HomeFragment extends Fragment {
    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private Bitmap bitmap;
    private List<String> labels;
    private NavController navController;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        labels = ReadFile();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@Nullable View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = findNavController(this);
        //gallery button
        binding.gallery.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent, 100);
        });


        binding.camera.setOnClickListener(v -> {
            Intent camera = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(camera, 200);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data == null) return;
        if(requestCode == 100){
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), data.getData());
                classifyImage(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(requestCode == 200){
            bitmap = (Bitmap)data.getExtras().get("data");
            classifyImage(bitmap);
        }
    }

    private void classifyImage(Bitmap image){
        try {
            Model model = Model.newInstance(getContext());
            Bitmap resized = Bitmap.createScaledBitmap(bitmap,224, 224, true);

            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.UINT8);

            TensorImage tensorImage = TensorImage.fromBitmap(resized);
            ByteBuffer byteBuffer = tensorImage.getBuffer();

            inputFeature0.loadBuffer(byteBuffer);
            Model.Outputs outputs = model.process(inputFeature0);
            TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

            int index = getIndex(outputFeature0.getFloatArray());

            Bundle bundle = new Bundle();
            bundle.putString("TAG", labels.get(index));
            navController.navigate(R.id.navigation_recipe, bundle);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getIndex(float[] floatArray){
        int index = 0;
        float maxConfidences = 0;
        float[] confidences = floatArray;
        for(int i=0;i<confidences.length;i++){
            if(confidences[i] > maxConfidences){
                maxConfidences = confidences[i];
                index = i;
            }
        }
        return index;
    }

    private List<String> ReadFile(){
        BufferedReader reader = null;
        List<String> txt = new ArrayList<String>();
        try {
            reader = new BufferedReader(
                    new InputStreamReader(getContext().getAssets().open("labels.txt"), "UTF-8"));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                //process line
                txt.add(mLine);
                System.out.println(mLine);
            }
        } catch (IOException e) {
            //log the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }
        return txt;
    }
}