package com.example.remainme.remaindme.Services;

import com.example.remainme.remaindme.Models.ScreenItemGetSet;
import com.example.remainme.remaindme.R;

import java.util.ArrayList;
import java.util.List;

public class ArrayListCollections {

    public static List<ScreenItemGetSet> getSliderArrayValues() {

        List<ScreenItemGetSet> screenItemGetSetList = new ArrayList<>();
        screenItemGetSetList.add(new ScreenItemGetSet("1","1", R.drawable.ic_add_alert_black_24dp));
        screenItemGetSetList.add(new ScreenItemGetSet("2","2", R.drawable.ic_add_alert_black_24dp));
        screenItemGetSetList.add(new ScreenItemGetSet("3","3", R.drawable.ic_add_alert_black_24dp));
        return screenItemGetSetList;
    }

}
