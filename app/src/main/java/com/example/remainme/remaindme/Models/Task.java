package com.example.remainme.remaindme.Models;

/**
 * Created by Rck ~str~ villan on 12-Aug-20.
 */

public class Task {
    private int id ;
    private String title;
    private String schedule;
    private String done ;
    private String not_done;
    private String later;
    private int image;

    public  Task(int id,String title,String schedule,String done,String not_done,String later,int image){
        this.id=id;
        this.title=title;
        this.schedule=schedule;
        this.done=done;
        this.not_done=not_done;
        this.later=later;
        this.image=image;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSchedule() {
        return schedule;
    }

    public String getDone() {
        return done;
    }

    public String getNot_done() {
        return not_done;
    }

    public String getLater() {
        return later;
    }

    public int getImage() {
        return image;
    }
}
