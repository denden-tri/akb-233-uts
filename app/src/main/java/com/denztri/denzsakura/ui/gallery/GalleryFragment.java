package com.denztri.denzsakura.ui.gallery;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denztri.denzsakura.R;
import com.denztri.denzsakura.databinding.FragmentGalleryBinding;

import java.io.IOException;

/**
 * NIM                  : 10119233
 * NAMA                 : Denden Triana
 * Kelas                : IF-6
 * Tanggal Pengerjaan   : 27-04-2022
 **/

public class GalleryFragment extends Fragment {
    private GalleryListAdapter galleryListAdapter;
    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ProgressBar progressBar = binding.galleryCircleProgress;
        progressBar.setVisibility(View.VISIBLE);

        TextView textTitle = requireActivity().findViewById(R.id.appbar_title);
        textTitle.setText(R.string.title_gallery);

        initRecycle();

            new Handler().postDelayed(() -> {
                try {
                    galleryViewModel.getList().observe(getViewLifecycleOwner(),
                            galleryLists -> {
                                galleryListAdapter.setGalleryLists(galleryLists);
                                progressBar.setVisibility(View.GONE);
                            });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }, 1000);


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

}