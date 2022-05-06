package com.denztri.denzsakura.ui.media.tab.video;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denztri.denzsakura.databinding.FragmentVideoBinding;
import com.denztri.denzsakura.db.AppDatabase;
import com.denztri.denzsakura.db.Video;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class VideoFragment extends Fragment {

    private FragmentVideoBinding binding;

    private VideoListAdapter videoListAdapter;

    private AppDatabase db;

    private final Executor executor = Executors.newSingleThreadExecutor();

    public VideoFragment() {
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentVideoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        db = AppDatabase.getDbInstance(binding.getRoot().getContext());
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

    public void deleteAllList(){
        executor.execute(() -> db.videoDao().deleteAll());
    }

    public void repopulateList(){
        executor.execute(() -> db.videoDao().insert(Video.populateData()));
    }
}