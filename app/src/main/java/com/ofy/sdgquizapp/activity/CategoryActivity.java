package com.ofy.sdgquizapp.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.ofy.sdgquizapp.adapters.CategoryAdapter;
import com.ofy.sdgquizapp.helper.GridSpacingItemDecoration;
import com.ofy.sdgquizapp.helper.Session;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ofy.sdgquizapp.R;
import com.ofy.sdgquizapp.helper.AppController;
import com.ofy.sdgquizapp.Constant;
import com.ofy.sdgquizapp.helper.Utils;
import com.ofy.sdgquizapp.model.Category;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class CategoryActivity extends AppCompatActivity {
    private static int GRID_COLUMN=3;
    public RecyclerView recyclerView;
    public ProgressBar progressBar;
    public TextView empty_msg;
    public RelativeLayout layout;
    public ArrayList<Category> categoryList;
    public SwipeRefreshLayout swipeRefreshLayout;
    public Snackbar snackbar;
    public Toolbar toolbar;
    public AlertDialog alertDialog;
    CategoryAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_category);
        layout = findViewById(R.id.layout);

        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.select_category));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        empty_msg = findViewById(R.id.txtblanklist);
        progressBar = findViewById(R.id.progressBar);
        swipeRefreshLayout = findViewById(R.id.swipeLayout);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(CategoryActivity.this,GRID_COLUMN));


        Utils.GetSystemConfig(getApplicationContext());
        getData();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();

                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void getData() {
        progressBar.setVisibility(View.VISIBLE);
        if (Utils.isNetworkAvailable(CategoryActivity.this)) {
            getMainCategoryFromJson();
            invalidateOptionsMenu();

        } else {
            setSnackBar();
            progressBar.setVisibility(View.GONE);
        }

    }

    public void LanguageDialog() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(CategoryActivity.this);
        LayoutInflater inflater1 = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View dialogView = inflater1.inflate(R.layout.language_dialog, null);
        dialog.setView(dialogView);

        RecyclerView languageView = dialogView.findViewById(R.id.recyclerView);

        languageView.setLayoutManager(new LinearLayoutManager(CategoryActivity.this));
        alertDialog = dialog.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        Utils.GetLanguage(languageView, CategoryActivity.this, alertDialog);


    }


    public void setSnackBar() {
        snackbar = Snackbar
                .make(findViewById(android.R.id.content), getString(R.string.msg_no_internet), Snackbar.LENGTH_INDEFINITE)
                .setAction(getString(R.string.retry), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getData();
                    }
                });

        snackbar.setActionTextColor(Color.RED);
        snackbar.show();
    }

    /*
     * Get Quiz Category from Json
     */
    public void getMainCategoryFromJson() {
        progressBar.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.QUIZ_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            categoryList = new ArrayList<>();
                            JSONObject jsonObject = new JSONObject(response);
                            String error = jsonObject.getString(Constant.ERROR);
                            System.out.println("=====cate res " + response);
                            if (error.equalsIgnoreCase("false")) {
                                empty_msg.setVisibility(View.GONE);
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
                                adapter = new CategoryAdapter(CategoryActivity.this, categoryList);
                                if(recyclerView.getItemDecorationCount()==0) {
                                    //its getting added again and again at every refersh, so increasing the padding also
                                    recyclerView.addItemDecoration(new GridSpacingItemDecoration(GRID_COLUMN, 12));
                                }
                                recyclerView.setAdapter(adapter);
                                progressBar.setVisibility(View.GONE);
                            } else {
                                empty_msg.setText(getString(R.string.no_category));
                                progressBar.setVisibility(View.GONE);
                                empty_msg.setVisibility(View.VISIBLE);

                                if (adapter != null) {
                                    adapter = new CategoryAdapter(CategoryActivity.this, categoryList);
                                    recyclerView.setAdapter(adapter);
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
            /*
            @Override
            public void addMarker(String tag) {
                Log.i("TESTING"," Logging the tag: "+tag);
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                try{
                    if (response.headers.containsKey("Cache-Control")) {
                        response.headers.remove("Cache-Control");
                    }
                    if(response.headers.containsKey("Pragma")){
                        response.headers.remove("Pragma");
                    }
                    response.headers.put("expires","180000");
                    Cache.Entry cacheEntry = HttpHeaderParser.parseCacheHeaders(response);
                    if (cacheEntry == null) {
                        cacheEntry = new Cache.Entry();
                    }
                    final long cacheHitButRefreshed = 3 * 60 * 1000; // in 3 minutes cache will be hit, but also refreshed on background
                    final long cacheExpired = 24 * 60 * 60 * 1000; // in 24 hours this cache entry expires completely
                    long now = System.currentTimeMillis();
                    final long softExpire = now + cacheHitButRefreshed;
                    final long ttl = now + cacheExpired;
                    cacheEntry.data = response.data;
                    cacheEntry.softTtl = softExpire;
                    cacheEntry.ttl = ttl;
                    String headerValue;
                    headerValue = response.headers.get("Date");
                    if (headerValue != null) {
                        cacheEntry.serverDate = HttpHeaderParser.parseDateAsEpoch(headerValue);
                    }
                    headerValue = response.headers.get("Last-Modified");
                    if (headerValue != null) {
                        cacheEntry.lastModified = HttpHeaderParser.parseDateAsEpoch(headerValue);
                    }
//                    Log.d("TESTING", "parseNetworkResponse: "+response.headers);
                    cacheEntry.responseHeaders = response.headers;
                    final String jsonString = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers));
                    return Response.success(jsonString, cacheEntry);

                } catch (UnsupportedEncodingException e ) {
                    return Response.error(new ParseError(e));
                }
            }

            @Override
            protected void deliverResponse(String response) {
                super.deliverResponse(response);
            }
            @Override
            public void deliverError(VolleyError error) {
                super.deliverError(error);
            }
            @Override
            protected VolleyError parseNetworkError(VolleyError volleyError) {
                return super.parseNetworkError(volleyError);
            }
            */
        };

        AppController.getInstance().getRequestQueue().getCache().clear();
        AppController.getInstance().addToRequestQueue(stringRequest);
    }


    @Override
    public void onResume() {
        super.onResume();
        Utils.GetSystemConfig(getApplicationContext());
        invalidateOptionsMenu();
        if (Session.getBoolean(Session.LANG_MODE, getApplicationContext()))
            LanguageDialog();
//
//        Utils.loadNativeBannerAd(this,this,R.id.native_banner_card_container);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (snackbar != null) {
            snackbar.dismiss();
        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.cate_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (Session.getBoolean(Session.LANG_MODE, getApplicationContext()))
            menu.findItem(R.id.language).setVisible(true);
        else
            menu.findItem(R.id.language).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.language:
                if (alertDialog != null)
                    alertDialog.show();
                return true;

            case R.id.setting:
                Utils.CheckVibrateOrSound(CategoryActivity.this);
                Intent playQuiz = new Intent(CategoryActivity.this, SettingActivity.class);
                startActivity(playQuiz);
                overridePendingTransition(R.anim.open_next, R.anim.close_next);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
