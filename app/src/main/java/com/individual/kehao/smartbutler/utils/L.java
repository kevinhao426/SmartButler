package com.individual.kehao.smartbutler.utils;

import android.util.Log;

/*
 * Project Name: smartbutler
 * Package Name: com.individual.kehao.smartbutler.utils
 * File    Name: L
 * Create  By:   Ke Hao
 * Create  Time: 2018/7/24
 * Description : Logs
 */
public class L {

    public static final boolean DEBUG = true;

    public static final String TAG = "smartbutler";

    //DIWEF

    public static void  d(String text){
        if(DEBUG){
            Log.d(TAG, text);
        }
    }

    public static void  i(String text){
        if(DEBUG){
            Log.i(TAG, text);
        }
    }

    public static void  w(String text){
        if(DEBUG){
            Log.w(TAG, text);
        }
    }

    public static void  e(String text){
        if(DEBUG){
            Log.e(TAG, text);
        }
    }

}
