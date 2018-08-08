package com.individual.kehao.smartbutler.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.individual.kehao.smartbutler.MainActivity;
import com.individual.kehao.smartbutler.R;
import com.individual.kehao.smartbutler.utils.SharedUtils;
import com.individual.kehao.smartbutler.utils.StaticClass;
import com.individual.kehao.smartbutler.utils.UtilTools;

/*
 * Project Name: SmartButler
 * Package Name: com.individual.kehao.smartbutler.ui
 * File    Name: SplashActivity
 * Create  By:   Ke Hao
 * Create  Time: 2018/7/25
 * Description : a splash page that will delay for 2 seconds
 *               then if it is first running, go to GuideActivity
 *               or go to MainActivity
 *               * need to use SharedPreferences or SharedUtils which is packed in package utils
 *               * this page also changes the font
 */
public class SplashActivity extends AppCompatActivity{

    private TextView tv_splash;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case StaticClass.HANDLE_SPLASH:
                    if(isFirst()){
                        startActivity(new Intent(SplashActivity.this, GuideActivity.class));
                }else {
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    }
                    finish();
                    break;
            }
        }
    };

    private boolean isFirst() {
        Boolean isFirst = SharedUtils.getBoolean(this, StaticClass.IS_FIRST, true);

        if(isFirst){
            SharedUtils.putBoolean(this, StaticClass.IS_FIRST, false);
            return true;
        }else {
            return false;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        
        initView();
    }

    private void initView() {

        //delay for 2 seconds
        handler.sendEmptyMessageDelayed(StaticClass.HANDLE_SPLASH, 2000);

        tv_splash = findViewById(R.id.tv_splash);

        //set font
        UtilTools.setFont(this, tv_splash);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
