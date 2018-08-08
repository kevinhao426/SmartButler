package com.individual.kehao.smartbutler.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.individual.kehao.smartbutler.MainActivity;
import com.individual.kehao.smartbutler.R;
import com.individual.kehao.smartbutler.entity.MyUser;
import com.individual.kehao.smartbutler.utils.SharedUtils;
import com.individual.kehao.smartbutler.view.CustomDialog;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/*
 * Project Name: SmartButler
 * Package Name: com.individual.kehao.smartbutler.ui
 * File    Name: LoginActivity
 * Create  By:   Ke Hao
 * Create  Time: 2018/7/31
 * Description :
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_register;
    private EditText et_name;
    private EditText et_password;
    private Button btnLogin;
    private CheckBox keep_pass;
    private TextView forget_pass;

    private CustomDialog dialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(this);
        et_name = findViewById(R.id.etL_username);
        et_password = findViewById(R.id.etL_password);
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
        keep_pass = findViewById(R.id.keep_pass);
        forget_pass = findViewById(R.id.forget_Pass);
        forget_pass.setOnClickListener(this);

        dialog = new CustomDialog(this, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, R.layout.dialog_loading, R.style.Theme_dialog , Gravity.CENTER, R.style.pop_anim_style);

        dialog.setCancelable(false);

        boolean isChecked = SharedUtils.getBoolean(this, "keep_pass", false);
        keep_pass.setChecked(isChecked);
        if(isChecked){
            et_name.setText(SharedUtils.getString(this, "username", ""));
            et_password.setText(SharedUtils.getString(this, "password", ""));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;

            case R.id.forget_Pass:
                startActivity(new Intent(this, ForgetPassActivity.class));
                break;

            case R.id.btn_login:
                String name = et_name.getText().toString().trim();
                String password = et_password.getText().toString().trim();

                if (!TextUtils.isEmpty(name) & !TextUtils.isEmpty(password)) {

                    dialog.show();

                    //login
                    final MyUser user = new MyUser();
                    user.setUsername(name);
                    user.setPassword(password);
                    user.login(new SaveListener<MyUser>() {

                        @Override
                        public void done(MyUser myUser, BmobException e) {
                            dialog.dismiss();
                            if (e == null) {
                                SharedUtils.putBoolean(LoginActivity.this, "keep_pass", keep_pass.isChecked());
                                if (keep_pass.isChecked()) {
                                    SharedUtils.putString(LoginActivity.this, "username", et_name.getText().toString().trim());
                                    SharedUtils.putString(LoginActivity.this, "password", et_password.getText().toString().trim());
                                }else {
                                    SharedUtils.deleteOne(LoginActivity.this, "username");
                                    SharedUtils.deleteOne(LoginActivity.this, "password");
                                }
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();
                            } else {
                                Toast.makeText(LoginActivity.this, getString(R.string.toast_login_error) + e.getErrorCode(), Toast.LENGTH_SHORT);
                            }
                        }
                    });
                } else {
                    Toast.makeText(this, getString(R.string.toast_not_empty), Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
