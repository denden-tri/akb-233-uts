package com.denztri.denzsakura.ui.daily.tab.activity;

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

import com.denztri.denzsakura.databinding.FragmentActivityBinding;
import com.denztri.denzsakura.db.Activity;
import com.denztri.denzsakura.db.AppDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ActivityFragment extends Fragment {
    private ActivityListAdapter activityListAdapter;

    private FragmentActivityBinding binding;

    private final Executor executor = Executors.newSingleThreadExecutor();

    private AppDatabase db;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentActivityBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        db = AppDatabase.getDbInstance(binding.getRoot().getContext());

        initRecycle();
        loadActivityList();

        Handler handler = new Handler(Looper.getMainLooper());

        FloatingActionButton fabAct = binding.activityFab;
        fabAct.setOnClickListener(view -> Executors.newSingleThreadExecutor().execute(() -> {
            deleteAllList();
            repopulateList();
            handler.post(this::loadActivityList);
        }));

        return root;
    }


    private void initRecycle(){
        RecyclerView recyclerView = binding.activityRecycleView;
        recyclerView.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext(),
                LinearLayoutManager.VERTICAL, false));

        activityListAdapter = new ActivityListAdapter(binding.getRoot().getContext());
        recyclerView.setAdapter(activityListAdapter);
    }

    public void loadActivityList(){
         db.activityDao().getAllActivities().observe(getViewLifecycleOwner(),
                 activities -> activityListAdapter.setActivityList(activities));
    }

    public void deleteAllList(){
        executor.execute(() -> db.activityDao().deleteAll());
    }

    public void repopulateList(){
        executor.execute(() -> db.activityDao().insert(Activity.populateData()));
    }
}