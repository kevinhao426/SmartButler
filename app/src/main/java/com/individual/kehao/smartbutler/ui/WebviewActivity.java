package com.individual.kehao.smartbutler.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.individual.kehao.smartbutler.R;

/*
 * Project Name: SmartButler
 * Package Name: com.individual.kehao.smartbutler.ui
 * File    Name: WebviewActivity
 * Create  By:   Ke Hao
 * Create  Time: 2018/8/7
 * Description : news details
 */
public class WebviewActivity extends BaseActivity {

    private ProgressBar mProgressBar;
    private WebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        initView();
    }

    private void initView() {

        mProgressBar = findViewById(R.id.mProgressBar);
        mWebView = findViewById(R.id.mWebView);


        Intent intent = getIntent();

        String title = intent.getStringExtra("title");
        final String url = intent.getStringExtra("url");

        //set title
        getSupportActionBar().setTitle(title);

        //JS enabled
        mWebView.getSettings().setJavaScriptEnabled(true);
        //Zooming enabled
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setBuiltInZoomControls(true);

        mWebView.setWebChromeClient(new WebViewClient());

        mWebView.loadUrl(url);

        //open the website in app rather than system browser
        mWebView.setWebViewClient(new android.webkit.WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    private class WebViewClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100){
                mProgressBar.setVisibility(View.GONE);
            }
                super.onProgressChanged(view, newProgress);
        }
    }
}
