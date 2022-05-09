package com.denztri.denzsakura.ui.profile;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
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

import com.denztri.denzsakura.BuildConfig;
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
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

/**
 * NIM                  : 10119233
 * NAMA                 : Denden Triana
 * Kelas                : IF-6
 * Tanggal Pengerjaan   : 05-05-2022
 **/

public class ProfileFragment extends Fragment implements OnMapReadyCallback  {

    private FragmentProfileBinding binding;

    LatLngBounds.Builder builder = new LatLngBounds.Builder();
    List<Marker> markers = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProfileViewModel profileBinding =
                new ViewModelProvider(this).get(ProfileViewModel.class);


        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TextView textTitle = requireActivity().findViewById(R.id.appbar_title);
        textTitle.setText(R.string.title_profile);

        if (getActivity() != null && isAdded()){
            addMapFragment();
        }

        TextView igText = binding.profileContactIg;
        igText.setOnClickListener(view -> onCLickInstagram());

        TextView emailText = binding.profileContactEmail;
        emailText.setOnClickListener(view -> onClickSendEmail());

        TextView waText = binding.profileContactNumber;
        waText.setOnClickListener(view -> onClickWhatsApp());

        iconTintContact(igText);
        iconTintContact(emailText);
        iconTintContact(waText);

        MaterialButton aboutBtn = binding.profileDialogButton;
        aboutBtn.setOnClickListener(view -> showAbout());

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

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }


    private void addMapFragment(){
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                                            .findFragmentById(R.id.profile_map_findme);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        markers.add(googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(-6.9820635,107.8142747))
                .title("Me (Proximity) Home Amigo")));
        for (Marker m: markers
        ) {
            builder.include(m.getPosition());
        }
        LatLngBounds bounds = builder.build();

        int padding = 10;
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds,512,200, padding);
        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        googleMap.moveCamera(cu);
    }

    private void showAbout(){
        final Dialog dialog = new Dialog(this.getContext());
        dialog.setContentView(R.layout.dialog_profile_about);
        dialog.setTitle(R.string.subtitle_about);

        TextView aboutVersion = dialog.findViewById(R.id.dialog_about_version);
        TextView appName = dialog.findViewById(R.id.dialog_about_app_name);

        String version = "Version : " + BuildConfig.VERSION_NAME;

        appName.setText(R.string.app_name);
        aboutVersion.setText(version);

        dialog.show();
    }

    private void iconTintContact(TextView tView){
        int nighModeFlags = requireContext().getResources().getConfiguration().uiMode &
                Configuration.UI_MODE_NIGHT_MASK;
        if(nighModeFlags == Configuration.UI_MODE_NIGHT_YES){
            tView.getCompoundDrawablesRelative()[0].setTint(getResources().getColor(R.color.white));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}