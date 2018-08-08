package com.individual.kehao.smartbutler.application;

import android.app.Application;

import com.individual.kehao.smartbutler.utils.StaticClass;

import cn.bmob.v3.Bmob;

/*
 * Project Name: SmartButler
 * Package Name: com.individual.kehao.smartbutler.application
 * File    Name: BaseApplication
 * Create  By:   Ke Hao
 * Create  Time: 2018/7/25
 * Description :
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Bmob.initialize(this, StaticClass.BMOB_APP_ID);
    }
}
