package com.example.remainme.remaindme.Libs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.remainme.remaindme.R;

public class Constants {

    public static void moveNextPage(Context context, Class aClass) {
        context.startActivity(new Intent(context,aClass));
    }

    public void navigateToFragment(AppCompatActivity activity, Fragment fragment, int layout){
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(layout, fragment).commit();
        }
    }

}
