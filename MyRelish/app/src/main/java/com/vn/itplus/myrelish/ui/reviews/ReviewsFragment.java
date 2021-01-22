package com.vn.itplus.myrelish.ui.reviews;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.vn.itplus.myrelish.R;
import com.vn.itplus.myrelish.dto.ItemReviewArticle;
import com.vn.itplus.myrelish.viewAdapter.ListReviewArticleRecycleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ReviewsFragment extends Fragment {
    private static int LIST_QUERY_SIZE = 12;

    private ReviewsViewModel mViewModel;

    private RecyclerView listviewRecentReview;

    private ListReviewArticleRecycleAdapter listReviewAdapter;
    private ArrayList<ItemReviewArticle> listItemReview;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_reviews, container, false);

        listviewRecentReview = root.findViewById(R.id.listview_recent_review);

        loadRecentReview();

        return root;
    }

    private void loadRecentReview() {
        listItemReview = new ArrayList<>();
        listReviewAdapter = new ListReviewArticleRecycleAdapter(listItemReview, false);
        listviewRecentReview.setHasFixedSize(true);
        listviewRecentReview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        listviewRecentReview.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        listviewRecentReview.setAdapter(listReviewAdapter);

        final String url = getResources().getString(R.string.server_url) + "/show/lastReview/"+LIST_QUERY_SIZE;
        RequestQueue reqQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i=0; i<response.length(); i++){
                        JSONObject data = response.getJSONObject(i);
                        listItemReview.add(new ItemReviewArticle(
                                data.getString("title"),
                                data.getString("summary"),
                                data.getString("review"),
                                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(data.getString("created_time")),
                                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(data.getString("last_edit_time"))
                        ));
                    }
                    listReviewAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
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
}
