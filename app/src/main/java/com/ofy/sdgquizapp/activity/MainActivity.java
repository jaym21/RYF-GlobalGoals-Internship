package com.ofy.sdgquizapp.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.ofy.sdgquizapp.adapters.CategoryAdapter;
import com.ofy.sdgquizapp.R;
import com.ofy.sdgquizapp.helper.AppController;
import com.ofy.sdgquizapp.helper.BookmarkDBHelper;
import com.ofy.sdgquizapp.helper.CircleImageView;
import com.ofy.sdgquizapp.helper.DBHelper;
import com.ofy.sdgquizapp.helper.Session;
import com.ofy.sdgquizapp.helper.Utils;
import com.ofy.sdgquizapp.model.Category;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    private static int GRID_ROW = 3;
    private static int GRID_COLUMN=3;
    public static DBHelper dbHelper;
    public static BookmarkDBHelper bookmarkDBHelper;

    private SharedPreferences settings;
    public String type;

    private Toolbar toolbar ;
    private SwipeRefreshLayout refreshLayout;
    private ProgressBar progressBar;
    private AdView adView;
    private Button viewAllBtn;

    CategoryAdapter adapter;
    AlertDialog alertDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        //initializing admob
        MobileAds.initialize(this);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView = findViewById(R.id.adView);
        adView.loadAd(adRequest);

        //status bar color
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.bg_black));
        }


        //bottom navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);


        //setting home as selected
        bottomNavigationView.setSelectedItemId(R.id.bnHome);


            //making navigation on click
            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId()) {

                        case R.id.bnHome:
                            return true;

                        case R.id.bnQuiz:
                            startActivity(new Intent(MainActivity.this, QuizActivity.class));
                            finish();
                            return true;

                        case R.id.bnProfile:
                            if (Session.isLogin(getApplicationContext())) {
                                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                                finish();
                                return true;
                            }else {
                                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                                finish();
                                return true;
                            }

                    }

                    return false;
                }
            });

//


        viewAllBtn = findViewById(R.id.btnViewAll);

        viewAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.cdGoal8).setVisibility(View.VISIBLE);
                findViewById(R.id.llGoal9_10).setVisibility(View.VISIBLE);
                findViewById(R.id.llGoal11_12).setVisibility(View.VISIBLE);
                findViewById(R.id.llGoal13_14).setVisibility(View.VISIBLE);
                findViewById(R.id.llGoal15_16).setVisibility(View.VISIBLE);
                findViewById(R.id.llGoal17_18).setVisibility(View.VISIBLE);
                findViewById(R.id.btnViewAll).setVisibility(View.GONE);
            }
        });



        Utils.GetSystemConfig(getApplicationContext());

        setDatabaseHelpers();

//        initComponents();



        settings = getSharedPreferences(Session.SETTING_Quiz_PREF, 0);


//        type = getIntent().getStringExtra("type");
//        if (!type.equals("null")) {
//            if (type.equals("category")) {
//                Constant.TotalLevel = Integer.valueOf(getIntent().getStringExtra("maxLevel"));
//                Constant.CATE_ID = Integer.valueOf(getIntent().getStringExtra("cateId"));
//                if (getIntent().getStringExtra("no_of").equals("0")) {
//                    Intent intent = new Intent(MainActivity.this, LevelActivity.class);
//                    startActivity(intent);
//                } else {
//                    Intent intent = new Intent(MainActivity.this, SubcategoryActivity.class);
//                    startActivity(intent);
//                }
//            }
//        }

//        if (Utils.isNetworkAvailable(MainActivity.this)) {
//            if (Session.getBoolean(Session.LANG_MODE, getApplicationContext()))
//                LanguageDialog(MainActivity.this);
////            if (Session.isLogin(getApplicationContext())) {
////                GetUserStatus();
////            }
//
//        }

        //setting onClicks for global goals
        findViewById(R.id.cdGoal1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), GlobalGoals.class);
                intent.putExtra("goalno", "goal1");
                startActivity(intent);
            }
        });
        findViewById(R.id.cdGoal2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), GlobalGoals.class);
                intent.putExtra("goalno", "goal2");
                startActivity(intent);
            }
        });
        findViewById(R.id.cdGoal3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), GlobalGoals.class);
                intent.putExtra("goalno", "goal3");
                startActivity(intent);
            }
        });
        findViewById(R.id.cdGoal4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), GlobalGoals.class);
                intent.putExtra("goalno", "goal4");
                startActivity(intent);
            }
        });
        findViewById(R.id.cdGoal5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), GlobalGoals.class);
                intent.putExtra("goalno", "goal5");
                startActivity(intent);
            }
        });
        findViewById(R.id.cdGoal6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), GlobalGoals.class);
                intent.putExtra("goalno", "goal6");
                startActivity(intent);
            }
        });
        findViewById(R.id.cdGoal7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), GlobalGoals.class);
                intent.putExtra("goalno", "goal7");
                startActivity(intent);
            }
        });
        findViewById(R.id.cdGoal8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), GlobalGoals.class);
                intent.putExtra("goalno", "goal8");
                startActivity(intent);
            }
        });
        findViewById(R.id.cdGoal9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), GlobalGoals.class);
                intent.putExtra("goalno", "goal9");
                startActivity(intent);
            }
        });
        findViewById(R.id.cdGoal10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), GlobalGoals.class);
                intent.putExtra("goalno", "goal10");
                startActivity(intent);
            }
        });
        findViewById(R.id.cdGoal11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), GlobalGoals.class);
                intent.putExtra("goalno", "goal11");
                startActivity(intent);
            }
        });
        findViewById(R.id.cdGoal12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), GlobalGoals.class);
                intent.putExtra("goalno", "goal12");
                startActivity(intent);
            }
        });
        findViewById(R.id.cdGoal13).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), GlobalGoals.class);
                intent.putExtra("goalno", "goal13");
                startActivity(intent);
            }
        });
        findViewById(R.id.cdGoal14).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), GlobalGoals.class);
                intent.putExtra("goalno", "goal14");
                startActivity(intent);
            }
        });
        findViewById(R.id.cdGoal15).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), GlobalGoals.class);
                intent.putExtra("goalno", "goal15");
                startActivity(intent);
            }
        });
        findViewById(R.id.cdGoal16).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), GlobalGoals.class);
                intent.putExtra("goalno", "goal16");
                startActivity(intent);
            }
        });
        findViewById(R.id.cdGoal17).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), GlobalGoals.class);
                intent.putExtra("goalno", "goal17");
                startActivity(intent);
            }
        });


        findViewById(R.id.cdAfrica).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog("The UN system plays a crucial role in coordinating assistance of all kinds — to help Africa help itself.  From promoting the development of democratic institutions, to the establishment of peace between warring nations, the UN is present on the ground supporting economic and social development and the promotion and protection of human rights.");
            }
        });

        findViewById(R.id.cdAgeing).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog("The world’s population is ageing: virtually every country in the world is experiencing growth in the number and proportion of older persons in their population. The number of older persons, those aged 60 years or over, has increased substantially in recent years in most countries and regions, and that growth is projected to accelerate in the coming decades.");
            }
        });

        findViewById(R.id.cdAids).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog("New HIV infections have fallen by 35% since 2000 (by 58% among children) and AIDS-related deaths have fallen by 42% since the peak in 2004. The global response to HIV has averted 30 million new HIV infections and nearly 8 million  AIDS-related deaths since 2000.  The UN family has been in the vanguard of this progress.");
            }
        });

        findViewById(R.id.cdAtomicEnergy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog("More than 30 countries worldwide are operating 444 nuclear reactors for electricity generation and 66 new nuclear plants are under construction. In 2014, 13 countries relied on nuclear energy to supply at least one-quarter of their total electricity.");
            }
        });

        findViewById(R.id.cdBigData).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog("The volume of data in the world is increasing exponentially. New sources of data, new technologies, and new analytical approaches, if applied responsibly, can allow to better monitor progress toward achievement of the SDGs in a way that is both inclusive and fair.");
            }
        });

        findViewById(R.id.cdChildren).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog("Every child has the right to health, education and protection, and every society has a stake in expanding children’s opportunities in life. Yet, around the world, millions of children are denied a fair chance for no reason other than the country, gender or circumstances into which they are born.");
            }
        });

        findViewById(R.id.cdClimateChange).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog("Climate change is one of the major challenges of our time. From shifting weather patterns that threaten food production, to rising sea levels that increase the risk of catastrophic flooding, the impacts of climate change are global in scope and unprecedented in scale. ");
            }
        });

        findViewById(R.id.cdDecolonization).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog("TThe wave of decolonization, which changed the face of the planet, was born with the UN and represents the world body’s first great success. As a result of decolonization many countries became independent and joined the UN.");
            }
        });

        findViewById(R.id.cdDemocracy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog("Democracy is a universally recognized ideal and is one of the core values and principles of the United Nations. Democracy provides an environment for the protection and effective realization of human rights.");
            }
        });


        findViewById(R.id.cdEndingPoverty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog("While pre-pandemic global poverty rates had been cut by more than half since 2000, the COVID-19 pandemic could increase global poverty by as much as half a billion people, or 8% of the total human population.");
            }
        });

        findViewById(R.id.cdFood).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog("The world is not on track to achieve Sustainable Development Goal 2, Zero Hunger by 2030. The food security and nutritional status of the most vulnerable population groups is likely to deteriorate further due to the health and socio-economic impacts of the COVID-19 pandemic.");
            }
        });

        findViewById(R.id.cdGenderEquality).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog("Women and girls represent half of the world’s population and, therefore, also half of its potential. Gender equality, besides being a fundamental human right, is essential to achieve peaceful societies, with full human potential and sustainable development.");
            }
        });

        findViewById(R.id.cdHealth).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog("The United Nations, since its inception, has been actively involved in promoting and protecting good health worldwide. Leading that effort within the UN system is the World Health Organization (WHO), whose constitution came into force on 7 April 1948.");
            }
        });

        findViewById(R.id.cdHumanRights).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog("Promoting respect for human rights is a core purpose of the United Nations and defines its identity as an organization for people around the world. Member States have mandated the Secretary-General and the UN System to help them achieve the standards set out in the UN Charter and the Universal Declaration of Human Rights.");
            }
        });

        findViewById(R.id.cdLawJustice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog("The UN continues to promote justice and international law across its three pillars of work: international peace and security, economic and social progress and development, and respect for human rights and fundamental freedoms.");
            }
        });

        findViewById(R.id.cdMigration).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog("Since the earliest times, humanity has been on the move. Today, more people than ever before live in a country other than the one in which they were born.");
            }
        });

        findViewById(R.id.cdOcean).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog("Life itself arose from the oceans. The ocean is vast, some 72 per cent of the earth's surface. Not only has the oceans always been a prime source of nourishment for the life it helped generate, but from earliest recorded history it has served for trade and commerce, adventure and discovery.");
            }
        });

        findViewById(R.id.cdPeace).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog("Saving succeeding generations from the scourge of war was the main motivation for creating the United Nations, whose founders lived through the devastation of two world wars.");
            }
        });

        findViewById(R.id.cdPopulation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog("In 1950, five years after the founding of the United Nations, world population was estimated at around 2.6 billion people. It reached 5 billion in 1987 and 6 in 1999. In October 2011, the global population was estimated to be 7 billion.");
            }
        });

        findViewById(R.id.cdRefugees).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog("There were 79.5 million people forcibly displaced world-wide in 2019. Among those were 26 million refugees, half under the age of 18 (20.4 million refugees under UNHCR's mandate, and 5.6 million Palestine refugees under UNRWA's mandate).");
            }
        });

        findViewById(R.id.cdWater).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog("Fresh water sustains human life and is vital for human health. There is enough fresh water for everyone on Earth. However, due to bad economics or poor infrastructure, millions of people (most of them children) die from diseases associated with inadequate water supply, sanitation and hygiene.");
            }
        });

        findViewById(R.id.cdYouth).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog("As youth are increasingly demanding more just, equitable and progressive opportunities and solutions in their societies, the need to address the multifaceted challenges faced by young people (such as access to education, health, employment and gender equality) have become more pressing than ever.");
            }
        });
    }

    private void showCustomDialog(String issueDes) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.issues_dialog);
        dialog.setCancelable(true);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        TextView issueDescription = dialog.findViewById(R.id.tvIssueDes);

        issueDescription.setText(issueDes);

        dialog.findViewById(R.id.ivClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }


//    private void initComponents() {
//        toolbar = findViewById(R.id.hometoolBar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle(R.string.app_name);
//
//
//        welcomeNameText= findViewById(R.id.homeWelcomeText);
//        String name = Session.getUserData(Session.NAME,this);
//        if(name==null){
//            name="Guest";
//        }
//        welcomeNameText.setText(name);
//        viewMoreText = findViewById(R.id.homeViewMoreText);
//        viewMoreText.setOnClickListener(this);
//
//
//        bannerAdCardView = findViewById(R.id.native_banner_card_container);
//
//
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.home_menu, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        switch (item.getItemId()) {
//
//            case R.id.language:
//                if (alertDialog != null)
//                    alertDialog.show();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//
//    }

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onPause() {
        AppController.StopSound();
        super.onPause();
    }
//
//    private void setCategoryGrid() {
//        progressBar.setVisibility(View.VISIBLE);
//        final int spanCount = GRID_ROW*GRID_COLUMN;
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.QUIZ_URL,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            categoryList.clear();
//                            JSONObject jsonObject = new JSONObject(response);
//                            String error = jsonObject.getString(Constant.ERROR);
//                            System.out.println("=====cate res " + response);
//                            if (error.equalsIgnoreCase("false")) {
//                                JSONArray jsonArray = jsonObject.getJSONArray(Constant.DATA);
//                                for (int i = 0; i < jsonArray.length(); i++) {
//                                    Category category = new Category();
//                                    JSONObject object = jsonArray.getJSONObject(i);
//                                    category.setId(object.getString(Constant.ID));
//                                    category.setName(object.getString(Constant.CATEGORY_NAME));
//                                    category.setImage(object.getString(Constant.IMAGE));
//                                    category.setMaxLevel(object.getString(Constant.MAX_LEVEL));
//                                    category.setNoOfCate(object.getString(Constant.NO_OF_CATE));
//                                    categoryList.add(category);
//                                }
//                                adapter = new CategoryAdapter(MainActivity.this, categoryList,spanCount);
//                                if(recyclerView.getItemDecorationCount()==0) {
//                                    recyclerView.addItemDecoration(new GridSpacingItemDecoration(GRID_COLUMN, 30));
//                                }
//                                recyclerView.setAdapter(adapter);
//                            } else {
//                                if (adapter != null) {
//                                    adapter = new CategoryAdapter(MainActivity.this, categoryList,spanCount);
//                                    recyclerView.setAdapter(adapter);
//                                }
//                            }
//                            invalidateOptionsMenu();
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }finally {
//                            progressBar.setVisibility(View.GONE);
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                       //ToDO (Some actions to indicate failure to fetch category error)
//                        Log.d("TESTING", "onResponse: VolleyError caught:"+error.getMessage());
//                        progressBar.setVisibility(View.GONE);
//                    }
//                }) {
//
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put(Constant.accessKey, Constant.accessKeyValue);
//                if (Session.getBoolean(Session.LANG_MODE, getApplicationContext())) {
//                    params.put(Constant.GET_CATE_BY_LANG, "1");
//                    params.put(Constant.LANGUAGE_ID, Session.getCurrentLanguage(getApplicationContext()));
//                } else
//                    params.put(Constant.getCategories, "1");
//                return params;
//            }
//
//
//        };
//
////        AppController.getInstance().getRequestQueue().getCache().clear();
//        AppController.getInstance().addToRequestQueue(stringRequest);
//    }

//    public void GetUserStatus() {
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.QUIZ_URL,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            JSONObject obj = new JSONObject(response);
//                            boolean error = obj.getBoolean("error");
//                            if (!error) {
//                                JSONObject jsonobj = obj.getJSONObject("data");
//                                if (jsonobj.getString(Constant.status).equals(Constant.DE_ACTIVE)) {
//                                    Session.clearUserSession(getApplicationContext());
//                                    FirebaseAuth.getInstance().signOut();
//                                    LoginManager.getInstance().logOut();
//                                    Intent intentLogin = new Intent(MainActivity.this, LoginActivity.class);
//                                    startActivity(intentLogin);
//                                    finish();
//                                } else {
//                                    Constant.TOTAL_COINS = Integer.parseInt(jsonobj.getString(Constant.COINS));
//                                    Utils.postTokenToServer(getApplicationContext());
//                                    Utils.RemoveGameRoomId(FirebaseAuth.getInstance().getUid());
//                                }
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                    }
//                }) {
//
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put(Constant.accessKey, Constant.accessKeyValue);
//                params.put(Constant.GET_USER_BY_ID, "1");
//                params.put(Constant.ID, Session.getUserData(Session.USER_ID, getApplicationContext()));
//
//                return params;
//            }
//        };
//        AppController.getInstance().getRequestQueue().getCache().clear();
//        AppController.getInstance().addToRequestQueue(stringRequest);
//    }
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

    public void LanguageDialog(Activity activity) {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
        LayoutInflater inflater1 = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View dialogView = inflater1.inflate(R.layout.language_dialog, null);
        dialog.setView(dialogView);

        RecyclerView languageView = dialogView.findViewById(R.id.recyclerView);
        languageView.setLayoutManager(new LinearLayoutManager(activity));
        alertDialog = dialog.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        Utils.GetLanguage(languageView, activity, alertDialog);

    }


//    @Override
//    public void onClick(View view) {
//            switch (view.getId()) {
//             /*
//                case R.id.homeChallengeBtn:
//                    Utils.btnClick(view, MainActivity.this);
//                    if (!Session.isLogin(MainActivity.this)) {
//                        LoginPopUp();
//                    } else {
//                        // i commented the below code
//
////                        if (Session.getBoolean(Session.LANG_MODE, getApplicationContext())) {
////                            if (Session.getCurrentLanguage(getApplicationContext()).equals(Constant.D_LANG_ID)) {
////                                if (alertDialog != null)
////                                    alertDialog.show();
////                            } else {
////                                Intent intent = new Intent(MainActivity.this, GetOpponentActivity.class);
////                                startActivity(intent);
////                            //}
////                        } else {
////                            Intent intent = new Intent(MainActivity.this, GetOpponentActivity.class);
////                            startActivity(intent);
////                        }
//
//
//                        //i shortened the code
//                        Intent intent = new Intent(MainActivity.this, GetOpponentActivity.class);
//                        startActivity(intent);
//                    }
//                    break;
//                  */
//
////                case R.id.homeDailyQuizBtn:
////                    //Daily Quiz
////                    Toast.makeText(MainActivity.this,"Not Implemented",Toast.LENGTH_LONG).show();
////                    break;
//                case R.id.homeViewMoreText:
//                    Intent intent = new Intent(MainActivity.this,CategoryActivity.class);
//                    startActivity(intent);
//            }
//        }
//
    @Override
    protected void onResume() {
        super.onResume();
        AppController.playSound();
        if (Utils.isNetworkAvailable(MainActivity.this)) {
            Utils.GetSystemConfig(getApplicationContext());

//            if (Session.getBoolean(Session.LANG_MODE, getApplicationContext())) {
//                LanguageDialog(MainActivity.this);
//            }

            if (Session.isLogin(MainActivity.this)) {

                if (FirebaseAuth.getInstance().getUid() != null)
                    Utils.RemoveGameRoomId(FirebaseAuth.getInstance().getUid());
            }
        }

//        Utils.loadNativeBannerAd(this,this,R.id.native_banner_card_container);

    }


}
