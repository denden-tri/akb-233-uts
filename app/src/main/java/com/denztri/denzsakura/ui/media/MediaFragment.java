package com.denztri.denzsakura.ui.media;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.denztri.denzsakura.R;
import com.denztri.denzsakura.databinding.FragmentMediaBinding;
import com.denztri.denzsakura.ui.media.tab.music.MusicFragment;
import com.denztri.denzsakura.ui.media.tab.video.VideoFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

/**
 * NIM                  : 10119233
 * NAMA                 : Denden Triana
 * Kelas                : IF-6
 * Tanggal Pengerjaan   : 30-04-2022
 **/

public class MediaFragment extends Fragment {

    private FragmentMediaBinding binding;

    private ViewPager2 viewPager2;

    private MediaAdapter mediaAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentMediaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TextView textTitle = requireActivity().findViewById(R.id.appbar_title);
        textTitle.setText(R.string.title_media);

//        TabLayout tabLayout = binding.mediaTabLayout;
//        selectedTab(0);
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                selectedTab(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });

        return root;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewPager2 = view.findViewById(R.id.media_viewpager2);
        mediaAdapter = new MediaAdapter(this);
        viewPager2.setAdapter(mediaAdapter);
        viewPager2.setUserInputEnabled(false);
        TabLayout tabLayout = binding.mediaTabLayout;
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            if (position == 0){
                tab.setText("Music");
            } else {
                tab.setText("Video");
            }
        }).attach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void selectedTab(int pos){
        Fragment tabFragment;
        if (pos == 1) {
            tabFragment = new VideoFragment();
        } else {
            tabFragment = new MusicFragment();
        }

        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.media_linear, tabFragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }

    public static class MediaAdapter extends FragmentStateAdapter{

        public MediaAdapter(@NonNull Fragment fragment) {
            super(fragment);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            Fragment tabFragment;
            if (position == 1) {
                tabFragment = new VideoFragment();
            } else {
                tabFragment = new MusicFragment();
            }

            return tabFragment;
        }

        @Override
        public int getItemCount() {
            return 2;
        }
    }
}