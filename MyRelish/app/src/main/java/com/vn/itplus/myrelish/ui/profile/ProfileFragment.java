package com.vn.itplus.myrelish.ui.profile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.vn.itplus.myrelish.CollectionActivity;
import com.vn.itplus.myrelish.EditProfileActivity;
import com.vn.itplus.myrelish.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.TimeZone;

public class ProfileFragment extends Fragment {
    private TextView txt_username;
    private TextView txt_userBirthDay;
    private TextView txt_userBio;

    private ProfileViewModel profileViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

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
                intent.putExtra("FirstTab", 4);
                startActivity(intent);
            }
        });
        root.findViewById(R.id.btn_edit_profile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(intent);
            }
        });

        loadProfileData();

        return root;
    }

    public void loadProfileData(){
        RequestQueue reqQueue = Volley.newRequestQueue(getActivity());
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        if (preferences.contains("userId")){

        }else{

        }
        int userId = 1;
        final String url = getResources().getString(R.string.server_url)+"/user/"+userId;
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    if (response.length() <= 0){
                        Toast.makeText(getActivity(), "User's not exist", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    JSONObject profileData = response.getJSONObject(0);
                    Toast.makeText(getActivity(), profileData.toString(), Toast.LENGTH_SHORT).show();
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
}