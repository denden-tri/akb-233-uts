package com.denztri.denzsakura.ui.daily;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import com.denztri.denzsakura.R;

import com.denztri.denzsakura.databinding.FragmentDailyBinding;
import com.denztri.denzsakura.ui.daily.tab.activity.ActivityFragment;
import com.denztri.denzsakura.ui.daily.tab.friend.FriendFragment;
import com.google.android.material.tabs.TabLayout;

/**
 * NIM                  : 10119233
 * NAMA                 : Denden Triana
 * Kelas                : IF-6
 * Tanggal Pengerjaan   : 256-04-2022
 **/

public class DailyFragment extends Fragment {

    private FragmentDailyBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState){
        DailyViewModel dailyViewModel =
                new ViewModelProvider(this).get(DailyViewModel.class);

        binding = FragmentDailyBinding.inflate(inflater, container,false);
        View root = binding.getRoot();

        TextView textTitle = requireActivity().findViewById(R.id.appbar_title);
        textTitle.setText(R.string.title_daily);

        TabLayout tabLayout = binding.dailyTabLayout;
        selectedTab(0);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                selectedTab(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void selectedTab(int pos){
        Fragment tabFragment;
        if (pos == 1) {
            tabFragment = new FriendFragment();
        } else {
            tabFragment = new ActivityFragment();
        }

        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.daily_linear, tabFragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }
}