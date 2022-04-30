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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denztri.denzsakura.databinding.FragmentActivityBinding;
import com.denztri.denzsakura.db.Activity;
import com.denztri.denzsakura.db.AppDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.concurrent.Executors;

public class ActivityFragment extends Fragment {
    private ActivityListAdapter activityListAdapter;

    private FragmentActivityBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentActivityBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        initRecycle();

        loadActivityList();

        AppDatabase db = AppDatabase.getDbInstance(binding.getRoot().getContext());
        Handler handler = new Handler(Looper.getMainLooper());

        FloatingActionButton fabAct = binding.activityFab;
        fabAct.setOnClickListener(view -> Executors.newSingleThreadExecutor().execute(() -> {
            db.activityDao().deleteAll();
            db.activityDao().insert(Activity.populateData());
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
        AppDatabase db = AppDatabase.getDbInstance(binding.getRoot().getContext());
        List<Activity> activityList = db.activityDao().getAllActivities();
        activityListAdapter.setActivityList(activityList);
    }

}