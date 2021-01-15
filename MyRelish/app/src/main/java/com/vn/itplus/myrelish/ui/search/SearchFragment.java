package com.vn.itplus.myrelish.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.vn.itplus.myrelish.R;
import com.vn.itplus.myrelish.decorator.GridSpacingItemDecoration;
import com.vn.itplus.myrelish.dto.ItemProductCard;
import com.vn.itplus.myrelish.viewAdapter.ListProductCardRecycleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class SearchFragment extends Fragment {
    private static int GRID_COLUMN = 3;

    private SearchViewModel searchViewModel;

    private View viewConfig;
    private SearchView searchView;
    private RecyclerView listviewResult;
    private ImageButton btnSearchConfig;
    private RadioGroup radiogroupSearchType;
    private TextView textSearchMessage;

    private ArrayList<ItemProductCard> listItemSearchResult;
    private ListProductCardRecycleAdapter searchResultAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        searchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        View root = inflater.inflate(R.layout.fragment_search, container, false);

        mapping(root);

        listItemSearchResult = new ArrayList<>();
        searchResultAdapter = new ListProductCardRecycleAdapter(listItemSearchResult);
        listviewResult.setHasFixedSize(true);
        listviewResult.setLayoutManager(new GridLayoutManager(getContext(),GRID_COLUMN));
        listviewResult.addItemDecoration(new GridSpacingItemDecoration(GRID_COLUMN, 20, true));
        listviewResult.setAdapter(searchResultAdapter);

        toggleConfig(root);

        return root;
    }

    private void mapping(View root) {
        listviewResult = root.findViewById(R.id.listview_search_result);
        searchView = root.findViewById(R.id.search_view);
        viewConfig = root.findViewById(R.id.view_config);
        btnSearchConfig = root.findViewById(R.id.btn_search_config);
        radiogroupSearchType = root.findViewById(R.id.radiogroup_search_type);
        textSearchMessage = root.findViewById(R.id.text_search_message);

        btnSearchConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleConfig(v);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                int selectedId = radiogroupSearchType.getCheckedRadioButtonId();
                if (selectedId == R.id.radio_btn_game){
                    searchGame(query);
                }else if (selectedId == R.id.radio_btn_film){
                    searchMovie(query);
                }else if (selectedId == R.id.radio_btn_novel){
                    searchNovel(query);
                }
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        radiogroupSearchType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int selectedId = radiogroupSearchType.getCheckedRadioButtonId();
                String query = searchView.getQuery().toString();
                if (selectedId == R.id.radio_btn_game){
                    searchGame(query);
                }else if (selectedId == R.id.radio_btn_film){
                    searchMovie(query);
                }else if (selectedId == R.id.radio_btn_novel){
                    searchNovel(query);
                }
            }
        });
    }

    public void toggleConfig(View view){
        if (viewConfig.getVisibility() == View.VISIBLE){
            viewConfig.setVisibility(View.GONE);
        }else{
            viewConfig.setVisibility(View.VISIBLE);
        }
    }

    private void searchGame(String query){
        String url;
        try {
            url = getResources().getString(R.string.server_url) + "/search/name/game/" + URLEncoder.encode(query, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Toast.makeText(getContext(),"Từ khóa tìm kiếm không hợp lệ", Toast.LENGTH_SHORT).show();
            return;
        }

        RequestQueue reqQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                listItemSearchResult.clear();
                try {
                    listItemSearchResult.clear();
                    for (int i=0; i<response.length(); i++){
                        JSONObject data = response.getJSONObject(i);
                        listItemSearchResult.add(new ItemProductCard(
                                data.getString("header_img"),
                                data.getString("name")
                        ));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                textSearchMessage.setText("Tìm thấy "+listItemSearchResult.size()+" game");
                searchResultAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "There's error when connect. "+ error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        reqQueue.add(jsonObjectRequest);
    }
    private void searchMovie(String query){
        String url;
        try {
            url = getResources().getString(R.string.server_url) + "/search/name/film/" + URLEncoder.encode(query, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Toast.makeText(getContext(),"Từ khóa tìm kiếm không hợp lệ", Toast.LENGTH_SHORT).show();
            return;
        }

        RequestQueue reqQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                listItemSearchResult.clear();
                try {
                    for (int i=0; i<response.length(); i++){
                        JSONObject data = response.getJSONObject(i);
                        listItemSearchResult.add(new ItemProductCard(
                                data.getString("poster_img"),
                                data.getString("name")
                        ));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                textSearchMessage.setText("Tìm thấy "+listItemSearchResult.size()+" phim");
                searchResultAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "There's error when connect. "+ error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        reqQueue.add(jsonObjectRequest);
    }
    private void searchNovel(String query){
        String url;
        try {
            url = getResources().getString(R.string.server_url) + "/search/name/truyen/" + URLEncoder.encode(query, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Toast.makeText(getContext(),"Từ khóa tìm kiếm không hợp lệ", Toast.LENGTH_SHORT).show();
            return;
        }

        RequestQueue reqQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                listItemSearchResult.clear();
                try {
                    for (int i=0; i<response.length(); i++){
                        JSONObject data = response.getJSONObject(i);
                        listItemSearchResult.add(new ItemProductCard(
                                data.getString("cover_img"),
                                data.getString("name")
                        ));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                textSearchMessage.setText("Tìm thấy "+listItemSearchResult.size()+" truyện");
                searchResultAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "There's error when connect. "+ error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        reqQueue.add(jsonObjectRequest);
    }
}