package com.example.remainme.remaindme.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
<<<<<<< HEAD
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
=======
>>>>>>> 37fb3678985bfd67698ce38f7b5d7701c7c2a6ae
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

<<<<<<< HEAD
import com.example.remainme.remaindme.DataBaseHelper.DataBaseHelper;
import com.example.remainme.remaindme.MainActivity;
import com.example.remainme.remaindme.R;
=======
import com.example.remainme.remaindme.Adapters.TaskAdapter;
import com.example.remainme.remaindme.Fragments.AlarmFragment;
>>>>>>> 37fb3678985bfd67698ce38f7b5d7701c7c2a6ae
import com.example.remainme.remaindme.Lisitners.RecyclerTouchListener;
import com.example.remainme.remaindme.MainActivity;
import com.example.remainme.remaindme.Models.Task;
import com.example.remainme.remaindme.R;

import java.util.ArrayList;
import java.util.List;

public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    List<Task> taskList;
    RecyclerView recyclerView;
    TaskAdapter taskAdapter;
    DrawerLayout drawer;
    DataBaseHelper myDb;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
<<<<<<< HEAD
        taskList =new ArrayList<>();
        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskAdapter = new TaskAdapter(this,taskList);
        recyclerView.setAdapter(taskAdapter);
        myDb = new DataBaseHelper(this,"my_task");
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Task movie = taskList.get(position);
                Toast.makeText(getApplicationContext(), movie.getTitle() + " is fucked up!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {
=======
>>>>>>> 37fb3678985bfd67698ce38f7b5d7701c7c2a6ae

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BaseActivity.this, MainActivity.class));
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
       /* ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();*/

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
<<<<<<< HEAD
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void prepareData(){
//        taskList.add(new Task(1, "My first Title is here","Today","done","not_done","later",R.drawable.ic_schedule_black_24dp));
//        taskList.add(new Task(2, "My Second Title is here","Today","done","not_done","later",R.drawable.ic_schedule_black_24dp));
//        taskList.add(new Task(3, "My Third Title is here","Today","done","not_done","later",R.drawable.ic_schedule_black_24dp));
//        taskList.add(new Task(4, "My fourth Title is here","Today","done","not_done","later",R.drawable.ic_schedule_black_24dp));
//        taskList.add(new Task(5, "My fifth Title is here","Today","done","not_done","later",R.drawable.ic_schedule_black_24dp));
//        taskList.add(new Task(6, "My sixth Title is here","Today","done","not_done","later",R.drawable.ic_schedule_black_24dp));
//        taskList.add(new Task(7, "My seventh Title is here","Today","done","not_done","later",R.drawable.ic_schedule_black_24dp));
//        taskList.add(new Task(8, "My eigth Title is here","Today","done","not_done","later",R.drawable.ic_schedule_black_24dp));
//        taskList.add(new Task(9, "My nine Title is here","Today","done","not_done","later",R.drawable.ic_schedule_black_24dp));
//        taskList.add(new Task(10, "My ten Title is here","Today","done","not_done","later",R.drawable.ic_schedule_black_24dp));
//        taskList.add(new Task(11, "My eleven Title is here","Today","done","not_done","later",R.drawable.ic_schedule_black_24dp));
//        taskList.add(new Task(12, "My twelve Title is here","Today","done","not_done","later",R.drawable.ic_schedule_black_24dp));
        Cursor res = myDb.getAllData();
        if(res.getCount() == 0) {
            // show message
            showMessage("ReminderMe","Create Your Schedule");
            return;
        }
        while (res.moveToNext()) {
            taskList.add(new Task(Integer.parseInt(res.getString(0)), res.getString(1),res.getString(3),"done","not_done","later",R.drawable.ic_schedule_black_24dp));
        }
        taskAdapter.notifyDataSetChanged();
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
=======

>>>>>>> 37fb3678985bfd67698ce38f7b5d7701c7c2a6ae
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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
            if(drawer.isDrawerOpen(GravityCompat.END)){
                drawer.closeDrawer(GravityCompat.END);
            }else{
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

        if (id == R.id.nav_camera) {
            fragment = new AlarmFragment();
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fl_container_base, fragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.END);
        return true;
    }
}
