package com.denztri.denzsakura.ui.media.tab.video;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.ParcelableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denztri.denzsakura.databinding.FragmentVideoBinding;
import com.denztri.denzsakura.db.AppDatabase;
import com.denztri.denzsakura.db.Video;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class VideoFragment extends Fragment {

    private VideoViewModel mViewModel;

    private FragmentVideoBinding binding;

    private VideoListAdapter videoListAdapter;

    private AppDatabase db;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentVideoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        db = AppDatabase.getDbInstance(binding.getRoot().getContext());
        initRecycle();

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

        db.videoDao().getAllVideosId().observe(getViewLifecycleOwner(),
                videos -> {
                    videoListAdapter = new VideoListAdapter(this.getLifecycle(), videos);
                    recyclerView.setAdapter(videoListAdapter);
                });
    }

    private void loadVideoList(){
        db.videoDao().getAllVideosId().observe(getViewLifecycleOwner(),
                videos -> videoListAdapter.setVideoList(videos));
    }
}