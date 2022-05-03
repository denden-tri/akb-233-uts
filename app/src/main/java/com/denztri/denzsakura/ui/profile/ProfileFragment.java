package com.denztri.denzsakura.ui.profile;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.denztri.denzsakura.R;
import com.denztri.denzsakura.databinding.FragmentProfileBinding;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment  {

    private FragmentProfileBinding binding;

    Fragment findMeMap;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findMeMap =  MapFragment.newInstance();
        getChildFragmentManager().beginTransaction()
                .replace(R.id.profile_find_me_linear, findMeMap)
                .commit();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProfileViewModel profileBinding =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TextView textTitle = requireActivity().findViewById(R.id.appbar_title);
        textTitle.setText(R.string.title_profile);

        TextView igText = binding.profileContactIg;
        igText.setOnClickListener(view -> onCLickInstagram());

        TextView emailText = binding.profileContactEmail;
        emailText.setOnClickListener(view -> onClickSendEmail());

        TextView waText = binding.profileContactNumber;
        waText.setOnClickListener(view -> onClickWhatsApp());

        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    private void onCLickInstagram(){
        Uri uri = Uri.parse("https://instagram.com/_u/denzsakura");
        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

        likeIng.setPackage("com.instagram.android");

        try {
            startActivity(likeIng);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://instagram.com/denzsakura")));
        }
    }

    private void onClickSendEmail(){
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto","denzsakura@proton.me", null));
        startActivity(Intent.createChooser(intent, "Choose an Email client :"));
    }

    private void onClickWhatsApp() {
        String url = "https://api.whatsapp.com/send?phone=+6282216400093";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }


}