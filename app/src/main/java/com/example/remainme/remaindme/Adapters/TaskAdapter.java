package com.example.remainme.remaindme.Adapters;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.remainme.remaindme.Activities.BaseActivity;
import com.example.remainme.remaindme.DataBaseHelper.DataBaseHelper;
import com.example.remainme.remaindme.Fragments.NewTaskFragment;
import com.example.remainme.remaindme.R;
import com.example.remainme.remaindme.Models.Task;

import java.util.List;

/**
 * Created by Rck ~str~ villan on 12-Aug-20.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder>{
    private Context mCtx;
    private List<Task> taskList;
    int selected_position = -1;
    DataBaseHelper mdb;
    AlertDialog.Builder dialog=null;
    public TaskAdapter(Context mCtx,List<Task> taskList){
        this.mCtx=mCtx;
        this.taskList=taskList;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =LayoutInflater.from(mCtx);
        View view = layoutInflater.inflate(R.layout.task_list,null);
        mdb = new DataBaseHelper(mCtx,"my_task");
        dialog =new AlertDialog.Builder(mCtx);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TaskViewHolder holder, int position) {
        final Task task = taskList.get(position);
        holder.title.setText(task.getTitle());
        holder.schedule.setText(task.getSchedule());
        holder.done.setText(task.getDone());
        holder.not_done.setText(task.getNot_done());
        holder.later.setText(task.getLater());
        holder.imageButton.setImageDrawable(mCtx.getResources().getDrawable(task.getImage()));
        if(selected_position == position)
            holder.imageButton.setVisibility(View.GONE);
        else
            holder.imageButton.setVisibility(View.VISIBLE);
        holder.taskOptionMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                PopupMenu popupMenu = new PopupMenu(mCtx,holder.taskOptionMenu);
                popupMenu.inflate(R.menu.task_option_menus);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.menu1:
                                Cursor res=mdb.getFilterData(Integer.toString(task.getId()));
                                Bundle bundle=new Bundle();
                                while (res.moveToNext()) {
                                    bundle.putString("id", res.getString(0));
                                    bundle.putString("title", res.getString(1));
                                    bundle.putString("schedule", res.getString(2));
                                    bundle.putString("datetime", res.getString(3));
                                    bundle.putBoolean("isupdate", true);
                                }
                                NewTaskFragment newTaskFragment = new NewTaskFragment();
                                newTaskFragment.setArguments(bundle);
                                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                                FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
                                fragmentTransaction.replace(R.id.fl_container_base, newTaskFragment).commit();
                                break;
                            case R.id.menu2:
                                dialog.setMessage("Are you sure want to delete ?");
                                dialog.setTitle("RemindMe");
                                dialog.setIcon(R.drawable.ic_delete_black_24dp);
                                dialog.setPositiveButton("YES",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog,
                                                                int which) {
                                                Integer deletedRows = mdb.deleteData(Integer.toString(task.getId()));
                                                if(deletedRows > 0){
                                                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                                                    Intent refresh = new Intent(mCtx, BaseActivity.class);
                                                    activity.startActivity(refresh);
                                                    activity.finish();
                                                    Toast.makeText(mCtx,"Data Deleted",Toast.LENGTH_LONG).show();
                                                }
                                                else
                                                    Toast.makeText(mCtx,"Data not Deleted",Toast.LENGTH_LONG).show();
                                            }
                                        });
                                dialog.setNegativeButton("cancel",new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(mCtx,"cancel is clicked",Toast.LENGTH_LONG).show();
                                    }
                                });
                                AlertDialog alertDialog=dialog.create();
                                alertDialog.show();
                                break;
                            case R.id.menu3:

                                break;
                             default:
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if(selected_position != holder.getAdapterPosition())
//                    selected_position = holder.getAdapterPosition();
//                notifyDataSetChanged();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder{
        TextView title,schedule,done,not_done,later;
        ImageButton imageButton, taskOptionMenu;
        public TaskViewHolder(View itemView) {
            super(itemView);
            title        = itemView.findViewById(R.id.title);
            imageButton  = itemView.findViewById(R.id.varity);
            schedule     = itemView.findViewById(R.id.type);
            done         = itemView.findViewById(R.id.done);
            not_done     = itemView.findViewById(R.id.not_done);
            later        = itemView.findViewById(R.id.later);
            taskOptionMenu= itemView.findViewById(R.id.taskViewOptions);
        }
    }
}
