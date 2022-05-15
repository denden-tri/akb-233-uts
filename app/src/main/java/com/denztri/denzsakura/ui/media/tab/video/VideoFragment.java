package com.denztri.denzsakura.ui.media.tab.video;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denztri.denzsakura.R;
import com.denztri.denzsakura.databinding.FragmentVideoBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * NIM                  : 10119233
 * NAMA                 : Denden Triana
 * Kelas                : IF-6
 * Tanggal Pengerjaan   : 01-05-2022
 **/

public class VideoFragment extends Fragment {

    private FragmentVideoBinding binding;

    private VideoListAdapter videoListAdapter;

    private VideoViewModel videoViewModel;

    private final Executor executor = Executors.newSingleThreadExecutor();

    public VideoFragment() {
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentVideoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        videoViewModel = new ViewModelProvider(this).get(VideoViewModel.class);
        initRecycle();

        Handler handler = new Handler(Looper.getMainLooper());

        FloatingActionButton fab = binding.videoFab;
        fab.setOnClickListener(view -> Executors.newSingleThreadExecutor().execute(() -> {
            deleteAllList();
            repopulateList();
            handler.post(this::loadVideoList);
        }));

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
        videoListAdapter.setStateRestorationPolicy(RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY);
        recyclerView.setAdapter(videoListAdapter);
    }

    private void loadVideoList(){
        videoViewModel.getList().observe(getViewLifecycleOwner(), videos -> videoListAdapter.setVideoList(videos));
    }

    @Override
    public void onStart() {
        super.onStart();
        new Handler().postDelayed(this::loadVideoList, 1000);
    }

    public void deleteAllList(){
        executor.execute(() -> videoViewModel.deleteVideo());
    }

    public void repopulateList(){
        executor.execute(() -> videoViewModel.repopulateVideo());
    }
}