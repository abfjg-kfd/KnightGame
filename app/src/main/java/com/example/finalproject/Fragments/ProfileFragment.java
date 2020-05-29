package com.example.finalproject.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.finalproject.Item;
import com.example.finalproject.ItemAdapter;
import com.example.finalproject.R;

import java.util.Objects;

public class ProfileFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        TextView weaponInProfile = v.findViewById(R.id.weapon_in_profile);
        TextView chestInProfile = v.findViewById(R.id.chest_in_profile);
        TextView bootsInProfile = v.findViewById(R.id.boots_in_profile);
        TextView headInProfile = v.findViewById(R.id.head_in_profile);
        Item[] a = new Item[1];
        ItemAdapter itemAdapter = new ItemAdapter(getContext(), a);
        String weapon = itemAdapter.getAWeaponName(Objects.requireNonNull(getContext()));
        String chest = itemAdapter.getAChestName(getContext());
        String boots = itemAdapter.getABootsName(getContext());
        String head = itemAdapter.getAHeadName(getContext());
        headInProfile.setText(head);
        chestInProfile.setText(chest);
        weaponInProfile.setText(weapon);
        bootsInProfile.setText(boots);
        return v;
    }
}
