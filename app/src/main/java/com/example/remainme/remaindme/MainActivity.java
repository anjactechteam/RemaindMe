package com.example.remainme.remaindme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toast;

import com.example.remainme.remaindme.Adapters.TaskAdapter;
import com.example.remainme.remaindme.Lisitners.RecyclerTouchListener;
import com.example.remainme.remaindme.Models.Task;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Task> taskList;
    RecyclerView recyclerView;
    TaskAdapter taskAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        taskList =new ArrayList<>();
        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskAdapter = new TaskAdapter(this,taskList);
        recyclerView.setAdapter(taskAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Task movie = taskList.get(position);
                Toast.makeText(getApplicationContext(), movie.getSchedule() + " is fucked up!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        prepareData();
    }
public void prepareData(){
    taskList.add(new Task(1, "My first Title is here","Today","done","not_done","later",R.drawable.ic_schedule_black_24dp));
    taskList.add(new Task(2, "My Second Title is here","Today","done","not_done","later",R.drawable.ic_schedule_black_24dp));
    taskList.add(new Task(3, "My Third Title is here","Today","done","not_done","later",R.drawable.ic_schedule_black_24dp));
    taskList.add(new Task(4, "My fourth Title is here","Today","done","not_done","later",R.drawable.ic_schedule_black_24dp));
    taskList.add(new Task(5, "My fifth Title is here","Today","done","not_done","later",R.drawable.ic_schedule_black_24dp));
    taskList.add(new Task(6, "My sixth Title is here","Today","done","not_done","later",R.drawable.ic_schedule_black_24dp));
    taskList.add(new Task(7, "My seventh Title is here","Today","done","not_done","later",R.drawable.ic_schedule_black_24dp));
    taskList.add(new Task(8, "My eigth Title is here","Today","done","not_done","later",R.drawable.ic_schedule_black_24dp));
    taskList.add(new Task(9, "My nine Title is here","Today","done","not_done","later",R.drawable.ic_schedule_black_24dp));
    taskList.add(new Task(10, "My ten Title is here","Today","done","not_done","later",R.drawable.ic_schedule_black_24dp));
    taskList.add(new Task(11, "My eleven Title is here","Today","done","not_done","later",R.drawable.ic_schedule_black_24dp));
    taskList.add(new Task(12, "My twelve Title is here","Today","done","not_done","later",R.drawable.ic_schedule_black_24dp));
    taskAdapter.notifyDataSetChanged();
}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.activity_main_menu, menu);
        return true;
    }
}
