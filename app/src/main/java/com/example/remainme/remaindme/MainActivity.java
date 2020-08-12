package com.example.remainme.remaindme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Task> taskList;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskList =new ArrayList<>();
        taskList.add(new Task(1, "My first Title is here","Today","done","not_done","later",R.drawable.ic_schedule_black_24dp));
        taskList.add(new Task(2, "My Second Title is here","Today","done","not_done","later",R.drawable.ic_schedule_black_24dp));
        taskList.add(new Task(3, "My Third Title is here","Today","done","not_done","later",R.drawable.ic_schedule_black_24dp));
        taskList.add(new Task(3, "My Third Title is here","Today","done","not_done","later",R.drawable.ic_schedule_black_24dp));
        taskList.add(new Task(3, "My Third Title is here","Today","done","not_done","later",R.drawable.ic_schedule_black_24dp));
        taskList.add(new Task(3, "My Third Title is here","Today","done","not_done","later",R.drawable.ic_schedule_black_24dp));
        taskList.add(new Task(3, "My Third Title is here","Today","done","not_done","later",R.drawable.ic_schedule_black_24dp));
        taskList.add(new Task(3, "My Third Title is here","Today","done","not_done","later",R.drawable.ic_schedule_black_24dp));
        taskList.add(new Task(3, "My Third Title is here","Today","done","not_done","later",R.drawable.ic_schedule_black_24dp));
        taskList.add(new Task(3, "My Third Title is here","Today","done","not_done","later",R.drawable.ic_schedule_black_24dp));
        taskList.add(new Task(3, "My Third Title is here","Today","done","not_done","later",R.drawable.ic_schedule_black_24dp));
        taskList.add(new Task(3, "My Third Title is here","Today","done","not_done","later",R.drawable.ic_schedule_black_24dp));
        TaskAdapter taskAdapter = new TaskAdapter(this,taskList);
        recyclerView.setAdapter(taskAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.activity_main_menu, menu);
        return true;
    }
}
