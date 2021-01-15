package com.vn.itplus.myrelish;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.EventListener;

public class GameInfoActivity extends AppCompatActivity {
    private TextView textTitleTop, textTitle, textDescription,
            textReleaseDate, textDeveloper, textPublisher,
            textNumPeople, textNumReview, textScore,
            textIsGameInCollection;
    private ImageButton btnActionButton;
    private ImageView imageGameHeader;
    private RecyclerView listviewOtherReviews;
    private View btnAddToCollection, btnGoToMyReview;

    private void mapping() {
        textTitleTop = findViewById(R.id.text_title_top);
        textTitle = findViewById(R.id.text_title);
        textDescription = findViewById(R.id.text_description);
        textReleaseDate = findViewById(R.id.text_release_date);
        textDeveloper = findViewById(R.id.text_developer);
        textPublisher = findViewById(R.id.text_publisher);
        textNumPeople = findViewById(R.id.text_num_people);
        textNumReview = findViewById(R.id.text_num_reviews);
        textScore = findViewById(R.id.text_myrelish_score);
        textIsGameInCollection = findViewById(R.id.text_is_game_in_collection);

        btnActionButton = findViewById(R.id.btn_action_button);

        imageGameHeader = findViewById(R.id.image_game_header);

        listviewOtherReviews = findViewById(R.id.listview_other_reviews);

        btnAddToCollection = findViewById(R.id.btn_add_to_collection);
        btnGoToMyReview = findViewById(R.id.btn_goto_my_review);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_info);
        mapping();

        loadInfo(1);
        loadCount(1);
        loadOtherReviews(1);

        int gameId = getIntent().getIntExtra("id", -1);
        if (gameId >= 0){
            loadInfo(gameId);
            loadCount(gameId);
            loadOtherReviews(gameId);
        }
    }

    private void loadOtherReviews(int id) {

    }

    private void loadCount(int id) {
        final String url = getResources().getString(R.string.server_url) + "/search/name/game/count/" + id;
        RequestQueue reqQueue = Volley.newRequestQueue(getBaseContext());
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    if (response.length() <= 0){
                        Toast.makeText(getBaseContext(), "Can't find game info", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    JSONObject data = response.getJSONObject(0);

                    textNumPeople.setText(0);
                    textNumReview.setText(0);
                    textScore.setText(0);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getBaseContext(), "There's error when connect. "+ error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        reqQueue.add(jsonObjectRequest);
    }

    private void loadInfo(int id) {
        final String url = getResources().getString(R.string.server_url) + "/search/name/game/" + id;
        RequestQueue reqQueue = Volley.newRequestQueue(getBaseContext());
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    if (response.length() <= 0){
                        Toast.makeText(getBaseContext(), "Can't find game info", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    JSONObject data = response.getJSONObject(0);

                    final String img_url = data.getString("header_img");
                    Picasso.get().load(img_url).fit().centerCrop()
                            .placeholder(R.drawable.loading_2)
                            .error(R.drawable.set_video_game_doodle_6997_1231)
                            .into(imageGameHeader);

                    textTitleTop.setText(data.getString("name"));
                    textTitle.setText(data.getString("name"));
                    textDescription.setText(data.getString("description"));

                    textReleaseDate.setText(data.getString("release_date"));
                    textDeveloper.setText(data.getString("developer_id"));
                    textPublisher.setText(data.getString("publisher_id"));



                    if (true || data.getBoolean("in_collection")){
                        textIsGameInCollection.setText("Game đã có trong bộ sưu tập của bạn");
                        btnActionButton.setVisibility(View.GONE);

                        btnAddToCollection.setVisibility(View.GONE);
                        btnGoToMyReview.setVisibility(View.VISIBLE);

                        final int id = data.getInt("id");
                        btnAddToCollection.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                gotoMyReview(v, id);
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getBaseContext(), "There's error when connect. "+ error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        reqQueue.add(jsonObjectRequest);
    }

    public void addToCollection(View view){
        // check login
        // send request => change UI
    }

    public void goBack(View view){
        finish();
    }

    public void gotoMyReview(View view, int id){
        Intent intent = new Intent(getBaseContext(), GameReviewActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }
}
