package com.denztri.denzsakura.ui.media.tab.music;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denztri.denzsakura.R;
import com.denztri.denzsakura.databinding.FragmentMusicBinding;
import com.denztri.denzsakura.ui.gallery.GalleryListAdapter;

import java.util.List;

/**
 * NIM                  : 10119233
 * NAMA                 : Denden Triana
 * Kelas                : IF-6
 * Tanggal Pengerjaan   : 30-04-2022
 **/

public class MusicFragment extends Fragment {

    private FragmentMusicBinding binding;

    private MusicListAdapter musicListAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MusicViewModel musicViewModel =
                new ViewModelProvider(this).get(MusicViewModel.class);

        binding = FragmentMusicBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        initRecycle();

        musicViewModel.getList().observe(getViewLifecycleOwner(),
                musicLists -> musicListAdapter.setMusicLists(musicLists));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void initRecycle(){
        RecyclerView recyclerView = binding.musicRecycleView;
        recyclerView.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext(),
                LinearLayoutManager.VERTICAL,false));

        musicListAdapter = new MusicListAdapter(binding.getRoot().getContext());
        recyclerView.setAdapter(musicListAdapter);
    }
}