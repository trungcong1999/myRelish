package com.vn.itplus.myrelish;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.vn.itplus.myrelish.dto.ItemCriteriaReview;
import com.vn.itplus.myrelish.dto.ItemReviewArticle;
import com.vn.itplus.myrelish.viewAdapter.ListCriteriaRecycleAdapter;
import com.vn.itplus.myrelish.viewAdapter.ListReviewArticleRecycleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class GameReviewActivity extends AppCompatActivity {
    private TextView textTitle, textReleaseDate, textDeveloper, textPublisher,
            textCountCriteria, textCountReview;
    private RecyclerView listviewCriterias, listviewReviewArticles;

    private ArrayList<ItemCriteriaReview> listItemCriteriaReview;
    private ArrayList<ItemReviewArticle> listItemReviewArticle;

    private ListCriteriaRecycleAdapter listCriteriaRecycleAdapter;
    private ListReviewArticleRecycleAdapter listReviewArticleRecycleAdapter;

    private void mapping() {
        textTitle = findViewById(R.id.text_title);
        textReleaseDate = findViewById(R.id.text_release_date);
        textDeveloper = findViewById(R.id.text_developer);
        textPublisher = findViewById(R.id.text_publisher);

        textCountCriteria = findViewById(R.id.text_count_criteria);
        textCountReview = findViewById(R.id.text_count_review);

        listviewCriterias = findViewById(R.id.listview_criteria);
        listviewReviewArticles = findViewById(R.id.listview_my_review);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_review);
        mapping();

        listItemCriteriaReview = new ArrayList<>();
        listItemReviewArticle = new ArrayList<>();

        // NOTE: just for testing
        loadGameInfo(1);
        loadListCriteria(1, 1);
        loadListReviewArticle(1, 1);
        // end testing

        int gameId = getIntent().getIntExtra("id", -1);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        int userId = preferences.getInt("userId", -1);
        if (gameId >= 0 && userId >= 0){


            loadGameInfo(gameId);
            loadListCriteria(userId, gameId);
            loadListReviewArticle(userId, gameId);
        }
    }

    private void loadGameInfo(int gameId) {
        final String url = getResources().getString(R.string.server_url) + "/game/info/" + gameId;
        RequestQueue reqQueue = Volley.newRequestQueue(getBaseContext());
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject data = response;

                            textTitle.setText(data.getString("name"));
                            textReleaseDate.setText(data.getString("release_date"));
                            textDeveloper.setText(data.getString("developer_name"));
                            textPublisher.setText(data.getString("publisher_name"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getBaseContext(), "There's error when connect. "+ error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        reqQueue.add(request);
    }

    private void loadListCriteria(int userId, int gameId) {
        final String url = getResources().getString(R.string.server_url) + "/game/"+gameId+"/user/"+userId+"/all-criteria-reviewed";
        RequestQueue reqQueue = Volley.newRequestQueue(getBaseContext());
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    if (response.length() <= 0){
                        textCountCriteria.setText("Bạn chưa đánh giá game trên tiêu chí nào");
                    }else{
                        textCountCriteria.setText("Bạn đã đánh giá game trên "+response.length()+" tiêu chí");
                        for (int i=0; i<response.length(); i++){
                            JSONObject data = response.getJSONObject(i);
                            listItemCriteriaReview.add(new ItemCriteriaReview(
                                    data.getString("criteria_name"),
                                    data.getInt("score"),
                                    data.getString("review")
                            ));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                listCriteriaRecycleAdapter = new ListCriteriaRecycleAdapter(listItemCriteriaReview);
                listviewCriterias.setHasFixedSize(true);
                listviewCriterias.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false));
                listviewCriterias.addItemDecoration(new DividerItemDecoration(getBaseContext(), LinearLayoutManager.VERTICAL));
                listviewCriterias.setAdapter(listCriteriaRecycleAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getBaseContext(), "There's error when connect. "+ error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        reqQueue.add(jsonObjectRequest);
    }

    private void loadListReviewArticle(int userId, int gameId) {
        final String url = getResources().getString(R.string.server_url) + "/game/"+gameId+"/user/"+userId+"/all-review-articles";
        RequestQueue reqQueue = Volley.newRequestQueue(getBaseContext());
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    if (response.length() <= 0){
                        textCountReview.setText("Bạn chưa có bài viết nào");
                    }else{
                        textCountReview.setText("Bạn đã có "+response.length()+" bài viết");
                        for (int i=0; i<response.length(); i++){
                            JSONObject data = response.getJSONObject(i);
                            listItemReviewArticle.add(new ItemReviewArticle(
                                    data.getString("title"),
                                    data.getString("summary"),
                                    data.getString("review"),
                                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(data.getString("created_time")),
                                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(data.getString("last_edit_time"))
                            ));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                listReviewArticleRecycleAdapter = new ListReviewArticleRecycleAdapter(listItemReviewArticle);
                listviewReviewArticles.setHasFixedSize(true);
                listviewReviewArticles.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false));
                listviewReviewArticles.addItemDecoration(new DividerItemDecoration(getBaseContext(), LinearLayoutManager.VERTICAL));
                listviewReviewArticles.setAdapter(listReviewArticleRecycleAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getBaseContext(), "There's error when connect. "+ error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        reqQueue.add(jsonObjectRequest);
    }
}
