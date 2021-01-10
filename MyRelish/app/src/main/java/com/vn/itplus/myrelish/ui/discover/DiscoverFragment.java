package com.vn.itplus.myrelish.ui.discover;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.vn.itplus.myrelish.R;
import com.vn.itplus.myrelish.SimpleLoginActivity;

public class DiscoverFragment extends Fragment {

    private DiscoverViewModel discoverViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        discoverViewModel = new ViewModelProvider(this).get(DiscoverViewModel.class);
        View root = inflater.inflate(R.layout.fragment_discover, container, false);

        requireLogin();

        return root;
    }

    private void requireLogin(){
        // check login
        // NOTE: getContext() or get Activity() ?
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        if (preferences.contains("userId")){
            // user has login

        }else{
            // haven't login
            Intent intent = new Intent(this.getContext(), SimpleLoginActivity.class);
            startActivityForResult(intent, getResources().getInteger(R.integer.loginRequestCode));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == getResources().getInteger(R.integer.loginRequestCode)){
            if (resultCode == getResources().getInteger(R.integer.loginResultSuccessCode)){
                Toast.makeText(this.getContext(), "You logged in", Toast.LENGTH_SHORT).show();
            }else if (resultCode == getResources().getInteger(R.integer.loginResultSkipCode)){
                Toast.makeText(this.getContext(), "You skipped logging in", Toast.LENGTH_SHORT).show();
            }
        }
    }
}