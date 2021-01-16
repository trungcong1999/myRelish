package com.vn.itplus.myrelish.ui.discover;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.vn.itplus.myrelish.GameInfoActivity;
import com.vn.itplus.myrelish.GameReviewActivity;
import com.vn.itplus.myrelish.R;
import com.vn.itplus.myrelish.SimpleLoginActivity;
import com.vn.itplus.myrelish.decorator.GridSpacingItemDecoration;
import com.vn.itplus.myrelish.decorator.RecyclerItemClickListener;
import com.vn.itplus.myrelish.dto.ItemProductCard;
import com.vn.itplus.myrelish.viewAdapter.ListProductCardRecycleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DiscoverFragment extends Fragment {
    private static int LIST_QUERY_SIZE = 9;
    private static int GRID_COLUMN = 3;

    private DiscoverViewModel discoverViewModel;

    private RecyclerView listviewRecentGame, listviewRecentMovie, listviewRecentNovel;
    private ListProductCardRecycleAdapter listGameAdapter, listMovieAdapter, listNovelAdapter;
    private ArrayList<ItemProductCard> listGameItem, listMovieItem, listNovelItem;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        discoverViewModel = new ViewModelProvider(this).get(DiscoverViewModel.class);
        View root = inflater.inflate(R.layout.fragment_discover, container, false);

        mapping(root);
        requireLogin();

        listGameItem = new ArrayList<>();
        listGameAdapter = new ListProductCardRecycleAdapter(listGameItem);
        listviewRecentGame.setHasFixedSize(true);
        listviewRecentGame.setLayoutManager(new GridLayoutManager(getContext(),GRID_COLUMN));
        listviewRecentGame.addItemDecoration(new GridSpacingItemDecoration(GRID_COLUMN, 20, true));
        listviewRecentGame.setAdapter(listGameAdapter);

        listMovieItem = new ArrayList<>();
        listMovieAdapter = new ListProductCardRecycleAdapter(listMovieItem);
        listviewRecentMovie.setHasFixedSize(true);
        listviewRecentMovie.setLayoutManager(new GridLayoutManager(getContext(),GRID_COLUMN));
        listviewRecentMovie.addItemDecoration(new GridSpacingItemDecoration(GRID_COLUMN, 20, true));
        listviewRecentMovie.setAdapter(listMovieAdapter);

        listNovelItem = new ArrayList<>();
        listNovelAdapter = new ListProductCardRecycleAdapter(listNovelItem);
        listviewRecentNovel.setHasFixedSize(true);
        listviewRecentNovel.setLayoutManager(new GridLayoutManager(getContext(),GRID_COLUMN));
        listviewRecentNovel.addItemDecoration(new GridSpacingItemDecoration(GRID_COLUMN, 20, true));
        listviewRecentNovel.setAdapter(listNovelAdapter);

        loadListRecentNovel();
        loadListRecentMovie();
        loadListRecentGame();

        return root;
    }

    private void mapping(View root){
        listviewRecentGame = root.findViewById(R.id.listview_recent_game);
        listviewRecentMovie = root.findViewById(R.id.listview_recent_movie);
        listviewRecentNovel = root.findViewById(R.id.listview_recent_novel);
    }

    private void loadListRecentNovel() {
        final String url = getResources().getString(R.string.server_url) + "/show/lastNovel/" + LIST_QUERY_SIZE;
        RequestQueue reqQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i=0; i<response.length(); i++){
                        JSONObject data = response.getJSONObject(i);
                        listNovelItem.add(new ItemProductCard(
                                data.getInt("id"),
                                data.getString("cover_img"),
                                data.getString("name")
                        ));
                    }
                    listNovelAdapter.notifyDataSetChanged();
                    listviewRecentNovel.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), listviewRecentNovel,
                            new RecyclerItemClickListener.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    // start novel info activity
                                }

                                @Override
                                public void onItemLongClick(View view, int position) {
                                    // pass
                                }
                            }));
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "There's error when connect. "+ error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        reqQueue.add(jsonObjectRequest);
    }

    private void loadListRecentMovie() {
        final String url = getResources().getString(R.string.server_url) + "/show/lastFilm/" + LIST_QUERY_SIZE;
        RequestQueue reqQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i=0; i<response.length(); i++){
                        JSONObject data = response.getJSONObject(i);
                        listMovieItem.add(new ItemProductCard(
                                data.getInt("id"),
                                data.getString("poster_img"),
                                data.getString("name")
                        ));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                listMovieAdapter.notifyDataSetChanged();
                listviewRecentMovie.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), listviewRecentMovie,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                // start film info activity
                            }

                            @Override
                            public void onItemLongClick(View view, int position) {
                                // pass
                            }
                        }));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "There's error when connect. "+ error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        reqQueue.add(jsonObjectRequest);
    }

    private void loadListRecentGame() {
        final String url = getResources().getString(R.string.server_url) + "/show/lastGame/" + LIST_QUERY_SIZE;
        RequestQueue reqQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i=0; i<response.length(); i++){
                        JSONObject data = response.getJSONObject(i);
                        listGameItem.add(new ItemProductCard(
                                data.getInt("id"),
                                data.getString("header_img"),
                                data.getString("name")
                        ));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                listGameAdapter.notifyDataSetChanged();
                listviewRecentGame.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), listviewRecentGame,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Intent intent = new Intent(getContext(), GameInfoActivity.class);
                                intent.putExtra("id", listGameItem.get(position).getId());
                                startActivity(intent);
                            }

                            @Override
                            public void onItemLongClick(View view, int position) {
                                // pass
                            }
                        }));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "There's error when connect. "+ error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        reqQueue.add(jsonObjectRequest);
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