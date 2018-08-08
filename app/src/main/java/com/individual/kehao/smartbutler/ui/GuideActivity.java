package com.individual.kehao.smartbutler.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.individual.kehao.smartbutler.MainActivity;
import com.individual.kehao.smartbutler.R;

import java.util.ArrayList;
import java.util.List;

/*
 * Project Name: SmartButler
 * Package Name: com.individual.kehao.smartbutler.ui
 * File    Name: GuideActivity
 * Create  By:   Ke Hao
 * Create  Time: 2018/7/25
 * Description : Three guide pages for first app running.
 *               Each page has a small dot point, in different colour when be chosen or not
 */
public class GuideActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager mGuidePager;

    private List<View> mList = new ArrayList<>();
    private View view1, view2, view3;

    // tiny points

    private ImageView point01, point02, point03;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        initView();
    }

    private void initView() {
        mGuidePager = findViewById(R.id.mGuidePager);
        point01 = findViewById(R.id.point01);
        point02 = findViewById(R.id.point02);
        point03 = findViewById(R.id.point03);



        //set default pics for dot points
        setImage(true, false, false);

        view1 = View.inflate(this, R.layout.guide_item_01, null);
        view2 = View.inflate(this, R.layout.guide_item_02, null);
        view3 = View.inflate(this, R.layout.guide_item_03, null);

        view1.findViewById(R.id.toMain).setOnClickListener(this);
        view2.findViewById(R.id.toMain).setOnClickListener(this);
        view3.findViewById(R.id.toMain).setOnClickListener(this);


        mList.add(view1);
        mList.add(view2);
        mList.add(view3);

        //set adapter
        mGuidePager.setAdapter(new GuideAdapter());
        mGuidePager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        setImage(true, false, false);
                        break;
                    case 1:
                        setImage(false, true, false);
                        break;
                    case 2:
                        setImage(false, false, true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void setImage(boolean isSelected1, boolean isSelected2, boolean isSelected3) {
        if (isSelected1) {
            point01.setBackgroundResource(R.drawable.point_on);
        } else {
            point01.setBackgroundResource(R.drawable.point_off);
        }

        if (isSelected2) {
            point02.setBackgroundResource(R.drawable.point_on);
        } else {
            point02.setBackgroundResource(R.drawable.point_off);
        }

        if (isSelected3) {
            point03.setBackgroundResource(R.drawable.point_on);
        } else {
            point03.setBackgroundResource(R.drawable.point_off);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.toMain:
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
        }
    }

    class GuideAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ((ViewPager) container).addView(mList.get(position));
            return mList.get(position);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            ((ViewPager) container).removeView(mList.get(position));
//            super.destroyItem(container, position, object);
        }
    }


}
