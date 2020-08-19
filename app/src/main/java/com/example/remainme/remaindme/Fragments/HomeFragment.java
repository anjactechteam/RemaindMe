package com.example.remainme.remaindme.Fragments;

import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.remainme.remaindme.Adapters.TaskAdapter;
import com.example.remainme.remaindme.DataBaseHelper.DataBaseHelper;
import com.example.remainme.remaindme.Lisitners.RecyclerTouchListener;
import com.example.remainme.remaindme.Models.Task;
import com.example.remainme.remaindme.R;

import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<Task> taskList;
    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    DataBaseHelper myDb;
    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        taskList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        taskAdapter = new TaskAdapter(getContext(), taskList);
        recyclerView.setAdapter(taskAdapter);
        myDb = new DataBaseHelper(getContext(), "my_task");
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext().getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Task movie = taskList.get(position);
                Toast.makeText(getContext().getApplicationContext(), movie.getTitle() + " is fucked up!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        prepareData();

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void prepareData() {
        /*        taskList.add(new Task(1, "My first Title is here","Today","done","not_done","later",R.drawable.ic_schedule_black_24dp));
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
        taskList.add(new Task(12, "My twelve Title is here","Today","done","not_done","later",R.drawable.ic_schedule_black_24dp));*/
        Cursor res = myDb.getAllData();
        if (res.getCount() == 0) {
            // show message
            showMessage("ReminderMe", "Create Your Schedule");
            return;
        }
        while (res.moveToNext()) {
            taskList.add(new Task(Integer.parseInt(res.getString(0)), res.getString(1), res.getString(3), "done", "not_done", "later", R.drawable.ic_schedule_black_24dp));
        }
        taskAdapter.notifyDataSetChanged();
    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}