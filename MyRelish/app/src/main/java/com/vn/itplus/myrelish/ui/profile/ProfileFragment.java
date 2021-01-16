package com.vn.itplus.myrelish.ui.profile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.vn.itplus.myrelish.CollectionActivity;
import com.vn.itplus.myrelish.EditProfileActivity;
import com.vn.itplus.myrelish.R;
import com.vn.itplus.myrelish.SimpleLoginActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;

public class ProfileFragment extends Fragment {
    private TextView txt_username;
    private TextView txt_userBirthDay;
    private TextView txt_userBio;

    private View viewMessageRequireLogin;

    private ProfileViewModel profileViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        txt_username = root.findViewById(R.id.profile_username);
        txt_userBirthDay = root.findViewById(R.id.profile_birthday);
        txt_userBio = root.findViewById(R.id.profile_bio);

        root.findViewById(R.id.btn_more_game_collection).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CollectionActivity.class);
                intent.putExtra("FirstTab", 0);
                startActivity(intent);
            }
        });
        root.findViewById(R.id.btn_more_film_collection).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CollectionActivity.class);
                intent.putExtra("FirstTab", 1);
                startActivity(intent);
            }
        });
        root.findViewById(R.id.btn_more_novel_collection).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CollectionActivity.class);
                intent.putExtra("FirstTab", 2);
                startActivity(intent);
            }
        });
        root.findViewById(R.id.btn_edit_profile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoEditProfile(v);
            }
        });
        root.findViewById(R.id.btn_log_out).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout(v);
            }
        });

        viewMessageRequireLogin = root.findViewById(R.id.view_message_require_login);
        viewMessageRequireLogin.findViewById(R.id.btn_go_to_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SimpleLoginActivity.class);
                startActivityForResult(intent, getResources().getInteger(R.integer.loginRequestCode));
            }
        });

        requireLogin();
        return root;
    }

    public void gotoEditProfile(View view){
        Intent intent = new Intent(this.getContext(), EditProfileActivity.class);
        startActivity(intent);
    }
    public void logout(View view){
         // send logout request
        RequestQueue reqQueue = Volley.newRequestQueue(getActivity());

        final String url = getResources().getString(R.string.server_url)+"/user/api/logout";

        StringRequest logoutRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.remove("userId");
                        editor.apply();
                        viewMessageRequireLogin.setVisibility(View.VISIBLE);

                        try{
                            JSONObject jsonResult = new JSONObject(response);
                            Toast.makeText(getActivity(), jsonResult.getString("message"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "There's error when connect. "+ error.toString(), Toast.LENGTH_SHORT).show();
                    }
            });
        reqQueue.add(logoutRequest);
    }

    private void loadProfileData(){
        RequestQueue reqQueue = Volley.newRequestQueue(getContext());
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        int userId = preferences.getInt("userId", -1);
        final String url = getResources().getString(R.string.server_url)+"/user/"+userId;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject profileData = response;
//                    Toast.makeText(getActivity(), profileData.toString(), Toast.LENGTH_SHORT).show();
                    txt_username.setText(profileData.getString("name"));
                    txt_userBirthDay.setText(profileData.getString("birthday"));
                    txt_userBio.setText(profileData.getString("bio"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "There's error when connect. "+ error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        reqQueue.add(jsonObjectRequest);
    }

    private void requireLogin(){
        // check login
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        if (preferences.contains("userId")){
            loadProfileData();
        }else{
            viewMessageRequireLogin.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == getResources().getInteger(R.integer.loginRequestCode)){
            if (resultCode == getResources().getInteger(R.integer.loginResultSuccessCode)){
                Toast.makeText(this.getContext(), "You logged in", Toast.LENGTH_SHORT).show();
                loadProfileData();
                viewMessageRequireLogin.setVisibility(View.GONE);
            }else if (resultCode == getResources().getInteger(R.integer.loginResultSkipCode)){
                Toast.makeText(this.getContext(), "You skipped logging in", Toast.LENGTH_SHORT).show();
            }
        }
    }
}