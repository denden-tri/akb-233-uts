package com.denztri.denzsakura.ui.daily;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.denztri.denzsakura.R;

import com.denztri.denzsakura.databinding.FragmentDailyBinding;

public class DailyFragment extends Fragment {

    private FragmentDailyBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState){
        DailyViewModel dailyViewModel =
                new ViewModelProvider(this).get(DailyViewModel.class);

        binding = FragmentDailyBinding.inflate(inflater, container,false);
        View root = binding.getRoot();

        TextView textTitle = getActivity().findViewById(R.id.appbar_title);
        textTitle.setText(R.string.title_daily);

        final TextView textView = binding.textDaily;
        dailyViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}