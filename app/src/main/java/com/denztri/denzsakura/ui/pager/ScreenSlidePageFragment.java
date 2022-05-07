package com.denztri.denzsakura.ui.pager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.denztri.denzsakura.MainActivity;
import com.denztri.denzsakura.R;
import com.google.android.material.button.MaterialButton;

/**
 * NIM                  : 10119233
 * NAMA                 : Denden Triana
 * Kelas                : IF-6
 * Tanggal Pengerjaan   : 27-04-2022
 **/

public class ScreenSlidePageFragment extends Fragment {

    private ViewGroup mView;
    private MaterialButton buttonClose;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState){
        mView = (ViewGroup) inflater.inflate(R.layout.fragment_screen_slide_page,container,
                false);

        buttonClose = mView.findViewById(R.id.btnClose);
        buttonClose.setOnClickListener(view -> startActivity(new Intent(getActivity(), MainActivity.class)));

        return mView;
    }

}
