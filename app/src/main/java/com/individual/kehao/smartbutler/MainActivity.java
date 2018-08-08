package com.individual.kehao.smartbutler;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.individual.kehao.smartbutler.fragment.ButlerFragment;
import com.individual.kehao.smartbutler.fragment.PictureFragment;
import com.individual.kehao.smartbutler.fragment.UserFragment;
import com.individual.kehao.smartbutler.fragment.WechatFragment;
import com.individual.kehao.smartbutler.ui.SettingActivity;
import com.individual.kehao.smartbutler.utils.L;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {



    private TabLayout mTablayout;
    private ViewPager mViewpager;
    private List<String> mTitle;
    private List<Fragment> mFragment;
    private FloatingActionButton fab_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setElevation(0);

        initData();
        initView();
    }


    private void initData() {
        mTitle = new ArrayList<>();
        mTitle.add(getString(R.string.title1));
        mTitle.add(getString(R.string.title2));
        mTitle.add(getString(R.string.title3));
        mTitle.add(getString(R.string.title4));

        mFragment = new ArrayList<>();
        mFragment.add(new ButlerFragment());
        mFragment.add(new WechatFragment());
        mFragment.add(new PictureFragment());
        mFragment.add(new UserFragment());
    }

    private void initView() {
        fab_setting = findViewById(R.id.fab_setting);
        fab_setting.setOnClickListener(this);
        fab_setting.setVisibility(View.GONE);
        mTablayout = findViewById(R.id.mTabLayout);

        mViewpager = findViewById(R.id.mViewPager);
        mViewpager.setOffscreenPageLimit(mFragment.size());

        //swipe listener
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
//                Log.i("tag", "Pos: "+i);
                if(i==0){
                    fab_setting.setVisibility(View.GONE);
                }else{
                    fab_setting.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        mViewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return mFragment.get(i);
            }

            @Override
            public int getCount() {
                return mFragment.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return mTitle.get(position);
            }

        });

        mTablayout.setupWithViewPager(mViewpager);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fab_setting:
                startActivity(new Intent(this, SettingActivity.class));
                break;
        }
    }
}
