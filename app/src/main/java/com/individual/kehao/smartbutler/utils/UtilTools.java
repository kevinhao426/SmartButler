package com.individual.kehao.smartbutler.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

/*
 * Project Name: SmartButler
 * Package Name: com.individual.kehao.smartbutler.utils
 * File    Name: UtilTools
 * Create  By:   Ke Hao
 * Create  Time: 2018/7/25
 * Description :
 */
public class UtilTools {

    public static void setFont(Context mContext, TextView textView){
        Typeface fontType = Typeface.createFromAsset(mContext.getAssets(), "fonts/FONT.TTF");
        textView.setTypeface(fontType);
    }


}
