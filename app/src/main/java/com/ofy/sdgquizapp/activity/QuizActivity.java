package com.ofy.sdgquizapp.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.facebook.login.LoginManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.ofy.sdgquizapp.Constant;
import com.ofy.sdgquizapp.R;
import com.ofy.sdgquizapp.adapters.CategoryAdapter;
import com.ofy.sdgquizapp.helper.AppController;
import com.ofy.sdgquizapp.helper.BookmarkDBHelper;
import com.ofy.sdgquizapp.helper.DBHelper;
import com.ofy.sdgquizapp.helper.GridSpacingItemDecoration;
import com.ofy.sdgquizapp.helper.Session;
import com.ofy.sdgquizapp.helper.Utils;
import com.ofy.sdgquizapp.model.Category;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class QuizActivity extends AppCompatActivity {

    private static int GRID_COLUMN=2;
    public static DBHelper dbHelper;
    public static BookmarkDBHelper bookmarkDBHelper;
    private SharedPreferences settings;
    public String type;
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private ArrayList<Category> categoryList;
    CategoryAdapter adapter;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //status bar color
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.bg_black));
        }

        //bottom navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
        //setting quiz as selected
        bottomNavigationView.setSelectedItemId(R.id.bnQuiz);

            //making navigation on click
            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId()) {

                        case R.id.bnHome:
                            startActivity(new Intent(QuizActivity.this, MainActivity.class));
                            finish();
                            return true;

                        case R.id.bnQuiz:
                            return true;

                        case R.id.bnProfile:
                            if (Session.isLogin(getApplicationContext())) {
                                startActivity(new Intent(QuizActivity.this, ProfileActivity.class));
                                finish();
                                return true;
                            }else {
                                startActivity(new Intent(QuizActivity.this, LoginActivity.class));
                                finish();
                                return true;
                            }
                    }

                    return false;
                }
            });



        Utils.GetSystemConfig(getApplicationContext());

        setDatabaseHelpers();

        init();
        categoryList = new ArrayList<>();
        setCategoryGrid();



        settings = getSharedPreferences(Session.SETTING_Quiz_PREF, 0);


        type = getIntent().getStringExtra("type");

        if (type != "null") {
            if (type == "category" ) {
                Constant.TotalLevel = Integer.valueOf(getIntent().getStringExtra("maxLevel"));
                Constant.CATE_ID = Integer.valueOf(getIntent().getStringExtra("cateId"));
                if (getIntent().getStringExtra("no_of").equals("0")) {
                    Intent intent = new Intent(QuizActivity.this, LevelActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(QuizActivity.this, SubcategoryActivity.class);
                    startActivity(intent);
                }
            }
        }


        if (Utils.isNetworkAvailable(QuizActivity.this)) {
            if (Session.isLogin(getApplicationContext())) {
                GetUserStatus();
            }

        }


    }

    private void init() {

        //initializing views
        refreshLayout = findViewById(R.id.swipeRefreshQuiz);
        recyclerView = findViewById(R.id.rvCategories);

        //setting recyclerview as grid layout
        recyclerView.setLayoutManager(new GridLayoutManager(QuizActivity.this,GRID_COLUMN));

        //progress bar to show when getting data from backend
        progressBar = findViewById(R.id.pbQuizActivity);

        //setting the refresh layout function
        refreshLayout.setOnRefreshListener(() -> {
            //calling category retrieving again to refresh the view
            setCategoryGrid();
            refreshLayout.setRefreshing(false);
        });

    }

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onPause() {
        AppController.StopSound();
        super.onPause();
    }

    private void setCategoryGrid() {
        //showing loading sign while fetching data from backend
        progressBar.setVisibility(View.VISIBLE);
        //making request to url
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.QUIZ_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            categoryList.clear();
                            JSONObject jsonObject = new JSONObject(response);
                            String error = jsonObject.getString(Constant.ERROR);
                            System.out.println("=====cate res " + response);
                            if (error.equalsIgnoreCase("false")) {
                                JSONArray jsonArray = jsonObject.getJSONArray(Constant.DATA);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    Category category = new Category();
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    category.setId(object.getString(Constant.ID));
                                    category.setName(object.getString(Constant.CATEGORY_NAME));
                                    category.setImage(object.getString(Constant.IMAGE));
                                    category.setMaxLevel(object.getString(Constant.MAX_LEVEL));
                                    category.setNoOfCate(object.getString(Constant.NO_OF_CATE));
                                    categoryList.add(category);
                                }
                                adapter = new CategoryAdapter(QuizActivity.this, categoryList);
                                if(recyclerView.getItemDecorationCount()==0) {
                                    recyclerView.addItemDecoration(new GridSpacingItemDecoration(GRID_COLUMN, 30));
                                }
                                recyclerView.setAdapter(adapter);
                            } else {
                                if (adapter != null) {
                                    adapter = new CategoryAdapter(QuizActivity.this, categoryList);
                                    recyclerView.setAdapter(adapter);
                                }
                            }
                            invalidateOptionsMenu();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }finally {
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //ToDO (Some actions to indicate failure to fetch category error)
                        Log.d("TESTING", "onResponse: VolleyError caught:"+error.getMessage());
                        progressBar.setVisibility(View.GONE);
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put(Constant.accessKey, Constant.accessKeyValue);
                if (Session.getBoolean(Session.LANG_MODE, getApplicationContext())) {
                    params.put(Constant.GET_CATE_BY_LANG, "1");
                    params.put(Constant.LANGUAGE_ID, Session.getCurrentLanguage(getApplicationContext()));
                } else
                    params.put(Constant.getCategories, "1");
                return params;
            }


        };

//        AppController.getInstance().getRequestQueue().getCache().clear();
        AppController.getInstance().addToRequestQueue(stringRequest);
    }
    public void GetUserStatus() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.QUIZ_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            boolean error = obj.getBoolean("error");
                            if (!error) {
                                JSONObject jsonobj = obj.getJSONObject("data");
                                if (jsonobj.getString(Constant.status).equals(Constant.DE_ACTIVE)) {
                                    Session.clearUserSession(getApplicationContext());
                                    FirebaseAuth.getInstance().signOut();
                                    LoginManager.getInstance().logOut();
                                    Intent intentLogin = new Intent(QuizActivity.this, LoginActivity.class);
                                    startActivity(intentLogin);
                                    finish();
                                } else {
                                    Constant.TOTAL_COINS = Integer.parseInt(jsonobj.getString(Constant.COINS));
                                    Utils.postTokenToServer(getApplicationContext());
                                    Utils.RemoveGameRoomId(FirebaseAuth.getInstance().getUid());
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put(Constant.accessKey, Constant.accessKeyValue);
                params.put(Constant.GET_USER_BY_ID, "1");
                params.put(Constant.ID, Session.getUserData(Session.USER_ID, getApplicationContext()));

                return params;
            }
        };
        AppController.getInstance().getRequestQueue().getCache().clear();
        AppController.getInstance().addToRequestQueue(stringRequest);
    }
    private void setDatabaseHelpers() {
        try {
            dbHelper = new DBHelper(getApplicationContext());
            bookmarkDBHelper = new BookmarkDBHelper(getApplicationContext());
            dbHelper.createDatabase();
            bookmarkDBHelper.createDatabase();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }





    @Override
    protected void onResume() {
        super.onResume();
        AppController.playSound();
        if (Utils.isNetworkAvailable(QuizActivity.this)) {
            Utils.GetSystemConfig(getApplicationContext());

            invalidateOptionsMenu();
            if (Session.isLogin(QuizActivity.this)) {
                if (FirebaseAuth.getInstance().getUid() != null)
                    Utils.RemoveGameRoomId(FirebaseAuth.getInstance().getUid());
            }
        }

    }


}


