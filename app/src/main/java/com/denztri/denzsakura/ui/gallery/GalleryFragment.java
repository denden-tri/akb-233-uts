package com.denztri.denzsakura.ui.gallery;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.denztri.denzsakura.R;
import com.denztri.denzsakura.apicall.GalleryApi;
import com.denztri.denzsakura.databinding.FragmentGalleryBinding;
import com.denztri.denzsakura.db.Activity;
import com.denztri.denzsakura.db.AppDatabase;
import com.denztri.denzsakura.ui.daily.tab.ActivityListAdapter;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GalleryFragment extends Fragment {
    private GalleryListAdapter galleryListAdapter;
    List<GalleryList> galleryLists;
    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TextView textTitle = requireActivity().findViewById(R.id.appbar_title);
        textTitle.setText(R.string.title_gallery);

        initRecycle();


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            loadGalleryList();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void initRecycle(){
        RecyclerView recyclerView = binding.galleryRecycle;
        recyclerView.setLayoutManager(new GridLayoutManager(binding.getRoot().getContext(),
                3));

        galleryListAdapter = new GalleryListAdapter(binding.getRoot().getContext());
        recyclerView.setAdapter(galleryListAdapter);
    }

    public void loadGalleryList() throws IOException {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://us-central1-akb-uts-cloud-functions.cloudfunctions.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GalleryApi service = retrofit.create(GalleryApi.class);
        galleryLists = service.listGallery().execute().body();
        galleryListAdapter.setGalleryLists(galleryLists);
    }
}