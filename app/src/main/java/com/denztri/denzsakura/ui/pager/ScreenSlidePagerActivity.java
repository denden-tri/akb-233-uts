package com.denztri.denzsakura.ui.pager;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.splashscreen.SplashScreen;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.denztri.denzsakura.R;
import com.denztri.denzsakura.ui.page.FirstPageFragment;
import com.denztri.denzsakura.ui.page.SecondPageFragment;
import com.denztri.denzsakura.ui.page.ThirdPageFragment;

public class ScreenSlidePagerActivity extends FragmentActivity {
    private static final int NUM_PAGES = 3;

    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);

        viewPager = findViewById(R.id.pager);
        viewPager.setPageTransformer(new ZoomOutPageTransformer());
        FragmentStateAdapter pagerAdapter = new ScreenSlidePagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);
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
}
