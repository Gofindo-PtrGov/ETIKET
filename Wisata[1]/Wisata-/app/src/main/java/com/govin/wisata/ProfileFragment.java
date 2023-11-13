package com.govin.wisata;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

public class ProfileFragment extends Fragment {

    SharedPrefManager sharedPrefManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);


        sharedPrefManager  =new SharedPrefManager(getActivity());
        TextView name = view.findViewById(R.id.tvName);
        TextView id_user = view.findViewById(R.id.tvEmail);
        ImageView imageView = view.findViewById(R.id.imageViewProfile);

        LinearLayout btneditProfil = view.findViewById(R.id.btnEditProfile);
        LinearLayout btneditPas = view.findViewById(R.id.btnEditPassword);
        LinearLayout btnlogout = view.findViewById(R.id.btnLogOut);


        name.setText(sharedPrefManager.getNama());
        id_user.setText("Your ID : "+sharedPrefManager.getUsername());
        if (sharedPrefManager.getImage().equals("")){

        }else {

            Glide.with(getActivity()).load(Data.SERVER+"wisata/gambar/profil/"+sharedPrefManager.getImage()).centerCrop().into(imageView);
        }


        btneditProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), KelolaProfile.class));
            }
        });

        btneditPas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), KelolaProfile.class));
            }
        });

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN,false);
                startActivity(new Intent(getActivity(), Login.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                getActivity().finish();
            }
        });




        return  view;
    }
}
