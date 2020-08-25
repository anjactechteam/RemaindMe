package com.example.remainme.remaindme.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.example.remainme.remaindme.Adapters.WelcomeSliderAdapter;
import com.example.remainme.remaindme.Libs.Constants;
import com.example.remainme.remaindme.Models.ScreenItemGetSet;
import com.example.remainme.remaindme.R;
import com.example.remainme.remaindme.Services.ArrayListCollections;

import java.util.List;

public class WelcomeActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private Button btnNext, btnGetStarted;
    private WelcomeSliderAdapter welcomeSliderAdapter;
    private TabLayout tabIndicator;
    private int position = 0;
    private List<ScreenItemGetSet> screenItemGetSetList;
    private Animation btnAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set a fullscreen activity
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (restoredPrefData()){
            Constants.moveNextPage(getApplicationContext(), BaseActivity.class);
        }
        setContentView(R.layout.activity_welcome);
        // hide tha actionbar / toolbar
        getSupportActionBar().hide();

        tabIndicator = (TabLayout) findViewById(R.id.tl_welcome_slider);
        viewPager = (ViewPager) findViewById(R.id.vp_welcome_slider);
        btnNext = (Button) findViewById(R.id.btn_next_welcome);
        btnGetStarted = (Button) findViewById(R.id.btn_get_started_welcome);
        btnAnimation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.btn_animation);

        screenItemGetSetList = ArrayListCollections.getSliderArrayValues();
        welcomeSliderAdapter = new WelcomeSliderAdapter(this, screenItemGetSetList);
        viewPager.setAdapter(welcomeSliderAdapter);

        tabIndicator.setupWithViewPager(viewPager);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position = viewPager.getCurrentItem();
                if (position < screenItemGetSetList.size()) {
                    position++;
                    viewPager.setCurrentItem(position);
                }
                if (position == screenItemGetSetList.size()-1) {
                    loadLastScreen();
                }
            }
        });

        tabIndicator.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == screenItemGetSetList.size()-1){
                    loadLastScreen();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constants.moveNextPage(getApplicationContext(), BaseActivity.class);
                savePrefData();
            }
        });

    }

    private boolean restoredPrefData() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        Boolean isIntroSliderOpenedBefore = sharedPreferences.getBoolean("isIntroSliderOpened",false);
        return isIntroSliderOpenedBefore;
    }

    private void savePrefData() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isIntroSliderOpened", true);
        editor.commit();
    }

    private void loadLastScreen() {
        btnNext.setVisibility(View.GONE);
        tabIndicator.setVisibility(View.GONE);
        btnGetStarted.setVisibility(View.VISIBLE);
        // set animation in get started btn
        btnGetStarted.setAnimation(btnAnimation);
    }
}
