package com.denztri.denzsakura.ui.media.tab.music;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.denztri.denzsakura.R;
import com.denztri.denzsakura.databinding.FragmentMusicBinding;

import java.util.concurrent.Executors;

/**
 * NIM                  : 10119233
 * NAMA                 : Denden Triana
 * Kelas                : IF-6
 * Tanggal Pengerjaan   : 30-04-2022
 **/

public class MusicFragment extends Fragment {

    private FragmentMusicBinding binding;

    private MusicListAdapter musicListAdapter;

    private RecyclerView recyclerView;

    private MusicViewModel musicViewModel;

    private ProgressBar progressBar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        musicViewModel =
                new ViewModelProvider(this).get(MusicViewModel.class);

        binding = FragmentMusicBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        progressBar = binding.musicProg;

        initRecycle();

        SwipeRefreshLayout swipeLayout = binding.musicSwipe;
        swipeLayout.setProgressBackgroundColorSchemeResource(R.color.aquamarine);
        swipeLayout.setColorSchemeResources(
                R.color.gunmetal,
                R.color.cyan_process,
                R.color.carnation_pink,
                R.color.wild_blue_yonder);
        swipeLayout.setOnRefreshListener(() -> {
            recyclerView.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            musicListAdapter.stopPlayer();
            Executors.newSingleThreadExecutor().execute(() -> {
                musicViewModel.deleteMusic();
                new Handler(Looper.getMainLooper()).post(() -> {
                        musicViewModel.loadMusicList();
                        swipeLayout.setRefreshing(false);
                });
            });
        });

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        new Handler().postDelayed(() -> musicViewModel.getList().observe(getViewLifecycleOwner(),
                musicLists -> {
                    if (recyclerView.getVisibility() == View.GONE) recyclerView.setVisibility(View.VISIBLE);
                    musicListAdapter.setMusicLists(musicLists);
                    progressBar.setVisibility(View.GONE);
                }), 1000);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void initRecycle(){
        recyclerView = binding.musicRecycleView;
        recyclerView.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext(),
                LinearLayoutManager.VERTICAL,false));

        musicListAdapter = new MusicListAdapter(binding.getRoot().getContext());
        recyclerView.setAdapter(musicListAdapter);
    }

    @Override
    public void onPause() {
        super.onPause();
        musicListAdapter.stopPlayer();
    }
}