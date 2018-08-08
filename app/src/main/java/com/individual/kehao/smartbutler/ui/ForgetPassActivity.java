package com.individual.kehao.smartbutler.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.individual.kehao.smartbutler.R;
import com.individual.kehao.smartbutler.entity.MyUser;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/*
 * Project Name: SmartButler
 * Package Name: com.individual.kehao.smartbutler.ui
 * File    Name: ForgetPassActivity
 * Create  By:   Ke Hao
 * Create  Time: 2018/8/6
 * Description :
 */
public class ForgetPassActivity extends BaseActivity implements View.OnClickListener {

    private Button btn_submit;
    private EditText old_pass;
    private EditText new_pass;
    private EditText new_pass2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);

        initView();
    }

    private void initView() {
        btn_submit = findViewById(R.id.btn_submit);
        old_pass = findViewById(R.id.old_pass);
        new_pass = findViewById(R.id.new_pass);
        new_pass2 = findViewById(R.id.new_pass2);

        btn_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_submit:
                String old = old_pass.getText().toString().trim();
                String new1 = new_pass.getText().toString().trim();
                String new2 = new_pass2.getText().toString().trim();

                if(!TextUtils.isEmpty(old) & !TextUtils.isEmpty(new1) & !TextUtils.isEmpty(new2)){
                    if(new1.equals(new2)){
                        MyUser.updateCurrentUserPassword(old, new1, new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if (e == null){
                                    Toast.makeText(ForgetPassActivity.this, getString(R.string.succeed), Toast.LENGTH_SHORT).show();
                                    finish();
                                }else{
                                    Toast.makeText(ForgetPassActivity.this, getString(R.string.error)+e.getErrorCode(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }else {
                        Toast.makeText(this, getString(R.string.password_not_match), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this, getString(R.string.toast_not_empty), Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
