package com.denztri.denzsakura.ui.video;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denztri.denzsakura.R;
import com.denztri.denzsakura.databinding.FragmentVideoBinding;
import com.denztri.denzsakura.db.AppDatabase;

public class VideoFragment extends Fragment {

    private VideoViewModel mViewModel;

    private FragmentVideoBinding binding;

    private VideoListAdapter   videoListAdapter;

    private AppDatabase db;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentVideoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        db = AppDatabase.getDbInstance(binding.getRoot().getContext());

        initRecycle();

        loadVideoList();

        return root;
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        binding = null;
    }

    private void initRecycle(){
        RecyclerView recyclerView = binding.videoRecycleView;
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));

        videoListAdapter = new VideoListAdapter(this.getLifecycle());
        recyclerView.setAdapter(videoListAdapter);
    }

    private void loadVideoList(){
        db.videoDao().getAllVideosId().observe(getViewLifecycleOwner(),
                videos -> videoListAdapter.setVideoList(videos));
    }
}