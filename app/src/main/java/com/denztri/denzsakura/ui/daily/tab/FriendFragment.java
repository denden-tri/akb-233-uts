package com.denztri.denzsakura.ui.daily.tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denztri.denzsakura.R;
import com.denztri.denzsakura.databinding.FragmentFriendBinding;
import com.denztri.denzsakura.db.AppDatabase;
import com.denztri.denzsakura.db.Friend;

import java.util.List;

public class FriendFragment extends Fragment {
    private FriendListAdapter friendListAdapter;

    private FragmentFriendBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentFriendBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        initRecycle();

        loadFriendList();

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
        AppDatabase db = AppDatabase.getDbInstance(binding.getRoot().getContext());
        List<Friend> friendList = db.friendDao().getAllFriends();
        friendListAdapter.setFriendList(friendList);
    }
}