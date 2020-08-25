package com.example.remainme.remaindme.Adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.remainme.remaindme.Models.ScreenItemGetSet;
import com.example.remainme.remaindme.R;

import java.util.List;

public class WelcomeSliderAdapter extends PagerAdapter {

    private Context context;
    private List<ScreenItemGetSet> screenItemGetSetList;

    public WelcomeSliderAdapter(Context context, List<ScreenItemGetSet> screenItemGetSetList) {
        this.context = context;
        this.screenItemGetSetList = screenItemGetSetList;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutScreen = layoutInflater.inflate(R.layout.layout_screen,null);

        ImageView imgSlider = layoutScreen.findViewById(R.id.iv_avatar);
        TextView tvTitle = layoutScreen.findViewById(R.id.tv_title);
        TextView tvDesc = layoutScreen.findViewById(R.id.tv_desc);

        tvTitle.setText(screenItemGetSetList.get(position).getTitle());
        tvDesc.setText(screenItemGetSetList.get(position).getDescription());
        imgSlider.setImageResource(screenItemGetSetList.get(position).getScreenimg());

        container.addView(layoutScreen);

        return layoutScreen;
    }

    @Override
    public int getCount() {
        return screenItemGetSetList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);

    }
}
