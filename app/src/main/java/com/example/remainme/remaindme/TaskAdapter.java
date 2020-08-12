package com.example.remainme.remaindme;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Rck ~str~ villan on 12-Aug-20.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder>{
    private Context mCtx;
    private List<Task> taskList;
    int selected_position = -1;
    TaskAdapter(Context mCtx,List<Task> taskList){
        this.mCtx=mCtx;
        this.taskList=taskList;
    }
    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =LayoutInflater.from(mCtx);
        View view = layoutInflater.inflate(R.layout.task_list,null);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TaskViewHolder holder, int position) {
        Task task = taskList.get(position);
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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selected_position != holder.getAdapterPosition())
                    selected_position = holder.getAdapterPosition();
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder{
        TextView title,schedule,done,not_done,later;
        ImageButton imageButton;
        public TaskViewHolder(View itemView) {
            super(itemView);
            title        = itemView.findViewById(R.id.title);
            imageButton  = itemView.findViewById(R.id.varity);
            schedule     = itemView.findViewById(R.id.type);
            done         = itemView.findViewById(R.id.done);
            not_done     = itemView.findViewById(R.id.not_done);
            later        = itemView.findViewById(R.id.later);
        }
    }
}
