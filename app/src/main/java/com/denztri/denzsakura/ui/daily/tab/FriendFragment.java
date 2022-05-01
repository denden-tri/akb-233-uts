package com.denztri.denzsakura.ui.daily.tab;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denztri.denzsakura.databinding.FragmentFriendBinding;
import com.denztri.denzsakura.db.Activity;
import com.denztri.denzsakura.db.AppDatabase;
import com.denztri.denzsakura.db.Friend;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class FriendFragment extends Fragment {
    private FriendListAdapter friendListAdapter;

    private FragmentFriendBinding binding;

    private final Executor executor = Executors.newSingleThreadExecutor();

    private AppDatabase db;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentFriendBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        db = AppDatabase.getDbInstance(binding.getRoot().getContext());

        initRecycle();

        loadFriendList();

        Handler handler = new Handler(Looper.getMainLooper());

        FloatingActionButton fabAct = binding.friendFab;
        fabAct.setOnClickListener(view -> Executors.newSingleThreadExecutor().execute(() -> {
            deleteAllList();
            repopulateList();
            handler.post(this::loadFriendList);
        }));

        return root;
    }

    private void initRecycle(){
        RecyclerView recyclerView = binding.friendRecycleView;
        recyclerView.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext(),
                LinearLayoutManager.HORIZONTAL, false));

        friendListAdapter = new FriendListAdapter(binding.getRoot().getContext());
        recyclerView.setAdapter(friendListAdapter);
    }

    public void loadFriendList(){
        db.friendDao().getAllFriends().observe(getViewLifecycleOwner(),
                friends -> friendListAdapter.setFriendList(friends));
    }

    public void deleteAllList(){
        executor.execute(() -> db.friendDao().deleteAll());
    }

    public void repopulateList(){
        executor.execute(() -> db.friendDao().insert(Friend.populateData()));
    }
}