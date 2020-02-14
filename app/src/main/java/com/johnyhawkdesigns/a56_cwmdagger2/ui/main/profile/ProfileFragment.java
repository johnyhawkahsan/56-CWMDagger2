package com.johnyhawkdesigns.a56_cwmdagger2.ui.main.profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.johnyhawkdesigns.a56_cwmdagger2.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import dagger.android.support.DaggerFragment;

public class ProfileFragment extends DaggerFragment {

    private static final String TAG = ProfileFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        Toast.makeText(getActivity(), "Profile Fragment", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onCreateView: Profile Fragment");

        return view;
    }
}
