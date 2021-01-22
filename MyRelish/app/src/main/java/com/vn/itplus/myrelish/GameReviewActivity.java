package com.vn.itplus.myrelish;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.vn.itplus.myrelish.decorator.RecyclerItemClickListener;
import com.vn.itplus.myrelish.dto.ItemCriteriaReview;
import com.vn.itplus.myrelish.dto.ItemGameCriteria;
import com.vn.itplus.myrelish.dto.ItemReviewArticle;
import com.vn.itplus.myrelish.viewAdapter.ListCriteriaRecycleAdapter;
import com.vn.itplus.myrelish.viewAdapter.ListReviewArticleRecycleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameReviewActivity extends AppCompatActivity {
    private TextView textTitle, textReleaseDate, textDeveloper, textPublisher,
            textCountCriteria, textCountReview,
            textTitleEditCriteria, textPreviewEditCriteriaScore, textCriteriaDescription;
    private RecyclerView listviewCriterias, listviewReviewArticles;

    private View btnAddCriteria, btnAddReview, containerEditCriteria;

    private ArrayList<ItemCriteriaReview> listItemCriteriaReview;
    private ArrayList<ItemReviewArticle> listItemReviewArticle;
    private ArrayList<ItemGameCriteria> listItemGameCriteria;

    private ArrayList<String> listItemGameCriteriaName;
    private ListCriteriaRecycleAdapter listCriteriaRecycleAdapter;
    private ListReviewArticleRecycleAdapter listReviewArticleRecycleAdapter;
    private ArrayAdapter listGameCriteriaArrayAdapter;

    private Button btnSubmitEditCriteria, btnCancelEditCriteria;

    private EditText edittextCriteriaReview;

    private Spinner spinnerSelectCriteria;
    private RatingBar ratingbarCriteriaScore;

    private boolean canEdit;
    private boolean isEditCriteria;
    private int edittingCriteriaId;

    private int userId, gameId;

    private void mapping() {
        textTitle = findViewById(R.id.text_title);
        textReleaseDate = findViewById(R.id.text_release_date);
        textDeveloper = findViewById(R.id.text_developer);
        textPublisher = findViewById(R.id.text_publisher);

        textCountCriteria = findViewById(R.id.text_count_criteria);
        textCountReview = findViewById(R.id.text_count_review);

        listviewCriterias = findViewById(R.id.listview_criteria);
        listviewReviewArticles = findViewById(R.id.listview_my_review);

        btnAddCriteria = findViewById(R.id.btn_add_criteria);
        btnAddReview = findViewById(R.id.btn_add_review);

        containerEditCriteria = findViewById(R.id.container_edit_criteria);
        btnSubmitEditCriteria = findViewById(R.id.btn_submit_edit_criteria);
        btnCancelEditCriteria = findViewById(R.id.btn_cancel_edit_criteria);

        textTitleEditCriteria = findViewById(R.id.text_title_edit_criteria);
        textPreviewEditCriteriaScore = findViewById(R.id.text_preview_criteria_score);
        textCriteriaDescription = findViewById(R.id.text_criteria_description);

        spinnerSelectCriteria = findViewById(R.id.spinner_select_criteria);
        ratingbarCriteriaScore = findViewById(R.id.rating_bar_criteria_score);

        edittextCriteriaReview = findViewById(R.id.edittext_criteria_review);

        ratingbarCriteriaScore.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                textPreviewEditCriteriaScore.setText(""+(int)rating);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_review);
        mapping();

        listItemCriteriaReview = new ArrayList<>();
        listItemReviewArticle = new ArrayList<>();
        listItemGameCriteria = new ArrayList<>();
        listItemGameCriteriaName = new ArrayList<>();

        listviewCriterias.setHasFixedSize(true);
        listviewCriterias.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false));
        listviewCriterias.addItemDecoration(new DividerItemDecoration(getBaseContext(), LinearLayoutManager.VERTICAL));

        listviewReviewArticles.setHasFixedSize(true);
        listviewReviewArticles.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false));
        listviewReviewArticles.addItemDecoration(new DividerItemDecoration(getBaseContext(), LinearLayoutManager.VERTICAL));

        canEdit = false;

        gameId = getIntent().getIntExtra("gameId", -1);
        userId = getIntent().getIntExtra("userId", -1);
        if (gameId >= 0 && userId >= 0){
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            if (userId == preferences.getInt("userId", -1)){
                canEdit = true;
                loadListCriteria();
            }
            loadGameInfo(gameId);
            loadListCriteriaReview(userId, gameId);
            loadListReviewArticle(userId, gameId);
        }
        btnAddCriteria.setVisibility(canEdit ? View.VISIBLE : View.GONE);
        btnAddReview.setVisibility(canEdit ? View.VISIBLE : View.GONE);
        containerEditCriteria.setVisibility(View.GONE);
    }


    private void loadListCriteria(){
        final String url = getResources().getString(R.string.server_url) + "/game/list-criteria";
        RequestQueue reqQueue = Volley.newRequestQueue(getBaseContext());
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i=0; i<response.length(); i++){
                        JSONObject data = response.getJSONObject(i);
                        listItemGameCriteria.add(new ItemGameCriteria(
                                data.getInt("id"),
                                data.getString("name"),
                                data.optString("alt_name"),
                                data.optString("description")
                        ));
                        listItemGameCriteriaName.add(data.getString("name"));
                    }
                    listGameCriteriaArrayAdapter = new ArrayAdapter(getBaseContext(), android.R.layout.simple_spinner_item, listItemGameCriteriaName);
                    listGameCriteriaArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerSelectCriteria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            textCriteriaDescription.setText(listItemGameCriteria.get(position).getDescription());
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    spinnerSelectCriteria.setAdapter(listGameCriteriaArrayAdapter);
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

    private void loadListCriteriaReview(int userId, int gameId) {
        final String url = getResources().getString(R.string.server_url) + "/game/"+gameId+"/user/"+userId+"/all-criteria-reviewed";
        RequestQueue reqQueue = Volley.newRequestQueue(getBaseContext());
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    if (response.length() <= 0){
                        textCountCriteria.setText("Chưa có tiêu chí nào được đánh giá");
                    }else{
                        textCountCriteria.setText("Số tiêu chí được đánh giá: "+response.length());
                        for (int i=0; i<response.length(); i++){
                            JSONObject data = response.getJSONObject(i);
                            listItemCriteriaReview.add(new ItemCriteriaReview(
                                    data.getInt("user_id"),
                                    data.getInt("game_id"),
                                    data.getInt("criteria_id"),
                                    data.getString("criteria_name"),
                                    data.getInt("score"),
                                    data.optString("review")
                            ));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                listCriteriaRecycleAdapter = new ListCriteriaRecycleAdapter(listItemCriteriaReview, canEdit);
                listviewCriterias.setAdapter(listCriteriaRecycleAdapter);

                listviewCriterias.addOnItemTouchListener(new RecyclerItemClickListener(getBaseContext(), listviewCriterias,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                            }

                            @Override
                            public void onItemLongClick(View view, final int position) {
                                PopupMenu popup = new PopupMenu(getBaseContext(), view);
                                //Inflating the Popup using xml file
                                popup.getMenuInflater().inflate(R.menu.popup_menu_edit_delete, popup.getMenu());

                                //registering popup with OnMenuItemClickListener
                                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                    public boolean onMenuItemClick(MenuItem item) {
                                        if (item.getItemId() == R.id.menu_item_edit){
                                            openEditCriteria(position);
                                        }else if (item.getItemId() == R.id.menu_item_delete){
                                            deleteCriteriaReview(position);
                                        }
                                        return true;
                                    }
                                });

                                popup.show();//showing popup menu
                            }
                        }));
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
                        textCountReview.setText("Chưa có bài viết nào");
                    }else{
                        textCountReview.setText("Số bài viết: "+response.length());
                        for (int i=0; i<response.length(); i++){
                            JSONObject data = response.getJSONObject(i);
                            listItemReviewArticle.add(new ItemReviewArticle(
                                    data.getString("title"),
                                    data.optString("summary"),
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

                listReviewArticleRecycleAdapter = new ListReviewArticleRecycleAdapter(listItemReviewArticle, canEdit);
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

    public void openAddNewCriteria(View view){
        isEditCriteria = false;

        containerEditCriteria.setVisibility(View.VISIBLE);
        btnAddCriteria.setVisibility(View.GONE);

        // clear
        spinnerSelectCriteria.setEnabled(true);
        textTitleEditCriteria.setText("Thêm tiêu chí đánh giá");
        ratingbarCriteriaScore.setRating(0);
        edittextCriteriaReview.setText("");
        btnSubmitEditCriteria.setText("Thêm");
    }

    public void openEditCriteria(int editPositionInList){
        isEditCriteria = true;
        edittingCriteriaId = listItemCriteriaReview.get(editPositionInList).getCriteriaId();

        containerEditCriteria.setVisibility(View.VISIBLE);
        btnAddCriteria.setVisibility(View.GONE);

        // clear
        int posInSpinner = -1;
        for (int i=0;i<listItemGameCriteria.size();i++){
            if (listItemGameCriteria.get(i).getId() == listItemCriteriaReview.get(editPositionInList).getCriteriaId()){
                posInSpinner = i;
            }
        }
        spinnerSelectCriteria.setSelection(posInSpinner);
        spinnerSelectCriteria.setEnabled(false);
        textTitleEditCriteria.setText("Chỉnh sửa tiêu chí đánh giá");
        ratingbarCriteriaScore.setRating(listItemCriteriaReview.get(editPositionInList).getScore());
        edittextCriteriaReview.setText(listItemCriteriaReview.get(editPositionInList).getReview());
        btnSubmitEditCriteria.setText("Cập nhật");
    }

    public void submitEditCriteria(View view){
        edittextCriteriaReview.clearFocus();
        String selectedCriteriaName = spinnerSelectCriteria.getSelectedItem().toString();
        if (isEditCriteria){
            final int criteriaIdFinal = edittingCriteriaId;

            String url = getResources().getString(R.string.server_url)+"/game/edit-criteria-review";
            StringRequest request = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try{
                                JSONObject jsonResult = new JSONObject(response);
                                if (jsonResult.getBoolean("isSuccess")){
                                    for (int i=0;i<listItemCriteriaReview.size();i++){
                                        if (listItemCriteriaReview.get(i).getCriteriaId() == edittingCriteriaId){
                                            listItemCriteriaReview.get(i).setScore((int)(ratingbarCriteriaScore.getRating()));
                                            listItemCriteriaReview.get(i).setReview(edittextCriteriaReview.getText().toString());
                                            break;
                                        }
                                    }
                                    listCriteriaRecycleAdapter.notifyDataSetChanged();
                                    if (listItemCriteriaReview.size() <= 0){
                                        textCountCriteria.setText("Chưa có tiêu chí nào được đánh giá");
                                    }else{
                                        textCountCriteria.setText("Số tiêu chí được đánh giá: "+listItemCriteriaReview.size());
                                    }
                                    cancelEditCriteria(null);
                                }else{
                                    Toast.makeText(getBaseContext(), "Chỉnh sửa thất bại: "+jsonResult.getString("message"), Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(getBaseContext(), "Dữ liệu phản hồi không hợp lệ", Toast.LENGTH_SHORT).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getBaseContext(), "errorResponse "+error, Toast.LENGTH_SHORT).show();
                        }
                    }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("userId", ""+userId);
                    params.put("gameId", ""+gameId);
                    params.put("criteriaId", ""+criteriaIdFinal);
                    params.put("score",""+(int)(ratingbarCriteriaScore.getRating()));
                    params.put("review",edittextCriteriaReview.getText().toString());
                    return params;
                }

            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(request);
        }else{
            int criteriaId = -1;
            for (int i=0; i<listItemGameCriteria.size(); i++){
                if (listItemGameCriteria.get(i).getName() == selectedCriteriaName){
                    criteriaId = listItemGameCriteria.get(i).getId();
                }
            }
            if (criteriaId == -1){
                Toast.makeText(this, "Tiêu chí không hợp lệ.", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean checkAllowSelectCriteria = true;
            for (int i=0; i<listItemCriteriaReview.size(); i++){
                if (listItemCriteriaReview.get(i).getCriteriaId()==criteriaId){
                    checkAllowSelectCriteria = false;
                    break;
                }
            }
            if (!checkAllowSelectCriteria){
                Toast.makeText(this, "Tiêu chí mà bạn lựa chọn đã tồn tại.", Toast.LENGTH_SHORT).show();
                return;
            }

            final int criteriaIdFinal = criteriaId;

            String url = getResources().getString(R.string.server_url)+"/game/add-criteria-review";
            StringRequest request = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try{
                                JSONObject jsonResult = new JSONObject(response);
                                if (jsonResult.getBoolean("isSuccess")){
                                    listItemCriteriaReview.add(new ItemCriteriaReview(
                                            userId, gameId, criteriaIdFinal,
                                            spinnerSelectCriteria.getSelectedItem().toString(),
                                            (int)(ratingbarCriteriaScore.getRating()),
                                            edittextCriteriaReview.getText().toString()
                                    ));
                                    listCriteriaRecycleAdapter.notifyDataSetChanged();
                                    if (listItemCriteriaReview.size() <= 0){
                                        textCountCriteria.setText("Chưa có tiêu chí nào được đánh giá");
                                    }else{
                                        textCountCriteria.setText("Số tiêu chí được đánh giá: "+listItemCriteriaReview.size());
                                    }
                                    openAddNewCriteria(null);
                                }else{
                                    Toast.makeText(getBaseContext(), "Thêm thất bại: "+jsonResult.getString("message"), Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(getBaseContext(), "Dữ liệu phản hồi không hợp lệ", Toast.LENGTH_SHORT).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getBaseContext(), "errorResponse "+error, Toast.LENGTH_SHORT).show();
                        }
                    }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("userId", ""+userId);
                    params.put("gameId", ""+gameId);
                    params.put("criteriaId", ""+criteriaIdFinal);
                    params.put("score",""+(int)(ratingbarCriteriaScore.getRating()));
                    params.put("review",edittextCriteriaReview.getText().toString());
                    return params;
                }

            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(request);
        }
    }

    public void cancelEditCriteria(View view){
        containerEditCriteria.setVisibility(View.GONE);
        btnAddCriteria.setVisibility(View.VISIBLE);
        edittextCriteriaReview.clearFocus();
    }

    private void deleteCriteriaReview(final int positionInAdapter){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Xóa đánh giá");
        alert.setMessage("Bạn có chắc chắn muốn xóa?");
        alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                String url = getResources().getString(R.string.server_url)+"/game/delete-criteria-review";
                StringRequest request = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try{
                                    JSONObject jsonResult = new JSONObject(response);
                                    if (jsonResult.getBoolean("isSuccess")){
                                        listItemCriteriaReview.remove(positionInAdapter);
                                        listCriteriaRecycleAdapter.notifyDataSetChanged();
                                        if (listItemCriteriaReview.size() <= 0){
                                            textCountCriteria.setText("Chưa có tiêu chí nào được đánh giá");
                                        }else{
                                            textCountCriteria.setText("Số tiêu chí được đánh giá: "+listItemCriteriaReview.size());
                                        }
                                    }else{
                                        Toast.makeText(getBaseContext(), "Xóa thất bại: "+jsonResult.getString("message"), Toast.LENGTH_LONG).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(getBaseContext(), "Dữ liệu phản hồi không hợp lệ", Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getBaseContext(), "errorResponse "+error, Toast.LENGTH_SHORT).show();
                            }
                        }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("userId", ""+userId);
                        params.put("gameId", ""+gameId);
                        params.put("criteriaId", ""+listItemCriteriaReview.get(positionInAdapter).getCriteriaId());
                        params.put("score","");
                        params.put("review","");
                        return params;
                    }

                };

                RequestQueue requestQueue = Volley.newRequestQueue(getBaseContext());
                requestQueue.add(request);
            }
        });
        alert.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alert.show();
    }

    public void goBack(View view){
        finish();
    }

    public void gotoProfile(View view){
//        Intent intent = new Intent(getBaseContext(), .class);
//        intent.putExtra("gameId", gameId);
//        intent.putExtra("userId", userId);
//        startActivity(intent);
    }
}
