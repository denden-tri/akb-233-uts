package com.denztri.denzsakura.ui.pager;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.splashscreen.SplashScreen;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.denztri.denzsakura.MainActivity;
import com.denztri.denzsakura.R;
import com.denztri.denzsakura.ui.page.FirstPageFragment;
import com.denztri.denzsakura.ui.page.SecondPageFragment;
import com.denztri.denzsakura.ui.page.ThirdPageFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * NIM                  : 10119233
 * NAMA                 : Denden Triana
 * Kelas                : IF-6
 * Tanggal Pengerjaan   : 27-04-2022
 **/

public class ScreenSlidePagerActivity extends FragmentActivity {
    private static final int NUM_PAGES = 3;

    private ViewPager2 viewPager;

    FloatingActionButton fab;
    FloatingActionButton fab2;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        SplashScreen.installSplashScreen(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);

        viewPager = findViewById(R.id.pager);
        viewPager.setPageTransformer(new MarginPageTransformer(8));
        FragmentStateAdapter pagerAdapter = new ScreenSlidePagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setUserInputEnabled(false);

        fab =  findViewById(R.id.pager_fab);
        fab2 = findViewById(R.id.pager_fab_2);

        fab.setOnClickListener(view -> onClickFab());

        fab2.setOnClickListener(view -> onClickFab2());
    }

    @Override
    public void onBackPressed(){
        if(viewPager.getCurrentItem() == 0){
            super.onBackPressed();
        }else{
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    private static class ScreenSlidePagerAdapter extends FragmentStateAdapter{
        public ScreenSlidePagerAdapter(FragmentActivity fa){
            super(fa);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position){
            switch (position){
                case 0: return new FirstPageFragment();
                case 1: return new SecondPageFragment();
                case 2: return new ThirdPageFragment();
                default: return new ScreenSlidePageFragment();
            }
        }

        @Override
        public int getItemCount(){
            return NUM_PAGES;
        }
    }

    private void onClickFab(){
        switch (viewPager.getCurrentItem()){
            case 0:
                viewPager.setCurrentItem(1, true);
                fab2.setEnabled(true);
                break;
            case 1:
                fab.setImageResource(R.drawable.ic_baseline_check_circle_24);
                fab2.setEnabled(true);
                viewPager.setCurrentItem(2,true);
                break;
            case 2:
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                break;
        }
    }

    private void onClickFab2(){
        switch (viewPager.getCurrentItem()){
            case 0:
                fab2.setEnabled(true);
                break;
            case 1:
                fab2.setEnabled(false);
                viewPager.setCurrentItem(0,true);
                break;
            case 2:
                fab.setImageResource(R.drawable.ic_baseline_arrow_forward_24);
                viewPager.setCurrentItem(1,true);
                break;
        }
    }
}
