package com.individual.kehao.smartbutler.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.individual.kehao.smartbutler.R;
import com.individual.kehao.smartbutler.entity.MyUser;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/*
 * Project Name: SmartButler
 * Package Name: com.individual.kehao.smartbutler.ui
 * File    Name: RegisterActivity
 * Create  By:   Ke Hao
 * Create  Time: 2018/7/31
 * Description :
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_user;
    private EditText et_age;
    private EditText et_desc;
    private RadioGroup mRadioGrop;
    private EditText et_password;
    private EditText et_password2;
    private Button btn_register;

    private boolean gender = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
    }

    private void initView() {
        et_user = findViewById(R.id.et_user);
        et_age = findViewById(R.id.et_age);
        et_desc = findViewById(R.id.et_desc);
        et_password = findViewById(R.id.et_password);
        et_password2 = findViewById(R.id.et_password2);
        mRadioGrop = findViewById(R.id.mRadioGroup);
        btn_register = findViewById(R.id.btn_register);

        btn_register.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                String name = et_user.getText().toString().trim();
                String age = et_age.getText().toString().trim();
                String desc = et_desc.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                String password2 = et_password2.getText().toString().trim();

                if (!TextUtils.isEmpty(name) &
                        !TextUtils.isEmpty(age) &
                        !TextUtils.isEmpty(desc) &
                        !TextUtils.isEmpty(password)) {

                    if (password.equals(password2)) {

                        mRadioGrop.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                                if (i == R.id.rb_boy) {
                                    gender = true;
                                } else if (i == R.id.rb_girl) {
                                    gender = false;
                                }
                            }
                        });

                        if (TextUtils.isEmpty(desc)) {
                            desc = getString(R.string.Desc_Is_Empty);
                        }

                        MyUser user = new MyUser();

                        user.setUsername(name);
                        user.setPassword(password);
                        user.setAge(Integer.parseInt(age));
                        user.setSex(gender);
                        user.setDesc(desc);

                        user.signUp(new SaveListener<MyUser>() {
                            @Override
                            public void done(MyUser myUser, BmobException e) {
                                if(e==null){
                                    Toast.makeText(RegisterActivity.this, getString(R.string.succeed), Toast.LENGTH_SHORT).show();
                                    finish();
                                }else{
                                    Toast.makeText(RegisterActivity.this, getString(R.string.error)+e.toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else {
                        Toast.makeText(this, getString(R.string.password_not_match), Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(this, getString(R.string.toast_not_empty), Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
