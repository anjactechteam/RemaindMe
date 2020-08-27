package com.example.remainme.remaindme.Activities;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.remainme.remaindme.Dialogs.AlertDialogServices;
import com.example.remainme.remaindme.Fragments.AlarmFragment;
import com.example.remainme.remaindme.Fragments.HomeFragment;
import com.example.remainme.remaindme.Fragments.NewTaskFragment;
import com.example.remainme.remaindme.Fragments.ProfileFragment;
import com.example.remainme.remaindme.Fragments.SettingsFragment;
import com.example.remainme.remaindme.Libs.Constants;
import com.example.remainme.remaindme.R;

import java.util.List;

public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        NewTaskFragment.OnFragmentInteractionListener, AlertDialogServices.DialogListener {

    DrawerLayout drawer;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
       /* ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();*/

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        navigateToFragment(new HomeFragment());
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END);
        } else {
            FragmentManager fm = getSupportFragmentManager();
            List<Fragment> fragments = fm.getFragments();
            if (fragments != null && fragments.size() > 0) {
                if (fragments.get(fragments.size() - 1) instanceof HomeFragment) {
                    new AlertDialogServices(this).setOK("Yes").setIcon(R.mipmap.ic_launcher)
                            .setCancel("No").setTitle("Are you sure want to exit")
                            .setDialogResponseListner(this).showOnlyMsg();
                } else {
                    navigateToFragment(new HomeFragment());
                }
            } else super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.main_side_bar) {
            if (drawer.isDrawerOpen(GravityCompat.END)) {
                drawer.closeDrawer(GravityCompat.END);
            } else {
                drawer.openDrawer(GravityCompat.END);
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        Class aClass = null;

        if (id == R.id.nav_home) {
            fragment = new HomeFragment();
        } else if (id == R.id.nav_add_task) {
            fragment = new NewTaskFragment();
        } else if (id == R.id.nav_profile) {
            fragment = new ProfileFragment();
        } else if (id == R.id.nav_settings) {
            fragment = new SettingsFragment();
        } else if (id == R.id.nav_share) {
            fragment = new AlarmFragment();
        } else if (id == R.id.nav_rate_us) {

        } else if (id == R.id.nav_feedback) {

        } else if (id == R.id.nav_about_us) {

        }

        if (aClass != null) {
//            startActivity(new Intent(BaseActivity.this, aClass));
            Constants.moveNextPage(BaseActivity.this, aClass);
        } else {
            navigateToFragment(fragment);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.END);
        return true;
    }

    public void navigateToFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fl_container_base, fragment).commit();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onDialogResponse(DialogInterface dialog, int which) {
        if (dialog.BUTTON_POSITIVE == which) {
            finishAffinity();
        } else {
            dialog.cancel();
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
