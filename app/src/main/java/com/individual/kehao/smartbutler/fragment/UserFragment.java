package com.individual.kehao.smartbutler.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.service.autofill.CustomDescription;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.individual.kehao.smartbutler.R;
import com.individual.kehao.smartbutler.entity.MyUser;
import com.individual.kehao.smartbutler.ui.LoginActivity;
import com.individual.kehao.smartbutler.utils.L;
import com.individual.kehao.smartbutler.utils.SharedUtils;
import com.individual.kehao.smartbutler.utils.StaticClass;
import com.individual.kehao.smartbutler.view.CustomDialog;

import java.io.File;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import de.hdodenhof.circleimageview.CircleImageView;

/*
 * Project Name: smartbutler
 * Package Name: com.individual.kehao.smartbutler.fragment
 * File    Name: BulterFragment
 * Create  By:   Ke Hao
 * Create  Time: 2018/7/24
 * Description : a problem exist here: the Radio group on checked change listener at line 125 will only available after 2nd edit profile. currently don't know why.
 */
public class UserFragment extends Fragment implements View.OnClickListener {

    private Button edit_user;
    private Button sign_out;
    private EditText et_username;
    private EditText et_gender;
    private EditText et_age;
    private EditText et_desc;
    private Button btn_update_ok;
    private RadioGroup eRadiogroup;


    private Button btn_camera;
    private Button btn_picture;
    private Button btn_cancel;

    private boolean tempGender = true;

    private void findView(View view) {
        edit_user = view.findViewById(R.id.edit_user);
        edit_user.setOnClickListener(this);

        sign_out = view.findViewById(R.id.sign_out);
        sign_out.setOnClickListener(this);

        et_username = view.findViewById(R.id.et_username);
        et_gender = view.findViewById(R.id.et_gender);
        et_age = view.findViewById(R.id.et_age);
        et_desc = view.findViewById(R.id.et_desc);
        eRadiogroup = view.findViewById(R.id.eRadiogroup);

        setEditTextEnabled(false);

        MyUser userInfo = BmobUser.getCurrentUser(MyUser.class);

        et_username.setText(userInfo.getUsername());
        et_age.setText(userInfo.getAge() + "");
        et_gender.setText(userInfo.isSex() ? getString(R.string.gender_male) : getString(R.string.gender_female));
        et_desc.setText(userInfo.getDesc());

        btn_update_ok = view.findViewById(R.id.btn_update_ok);
        btn_update_ok.setOnClickListener(this);

    }

    private void setEditTextEnabled(boolean is) {
        et_username.setEnabled(is);
        et_age.setEnabled(is);
        et_desc.setEnabled(is);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, null);
        findView(view);
        return view;
    }

    @Override
    public void onClick(final View view) {
        switch (view.getId()) {

            //click to edit file
            case R.id.edit_user:
                edit_user.setVisibility(View.GONE);
                et_gender.setVisibility(View.GONE);
                eRadiogroup.setVisibility(View.VISIBLE);
                setEditTextEnabled(true);
                btn_update_ok.setVisibility(View.VISIBLE);
                break;

            case R.id.btn_update_ok:
                String username = et_username.getText().toString();
                String age = et_age.getText().toString();
//                final String gender = et_gender.getText().toString();
                String desc = et_desc.getText().toString();

                if (!TextUtils.isEmpty(username) & !TextUtils.isEmpty(age)) {
                    final MyUser user = new MyUser();
                    user.setUsername(username);
                    user.setAge(Integer.parseInt(age));
//                    L.i("000");

                    eRadiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup radioGroup, int i) {
                            if (i == R.id.erb_boy) {
                                tempGender = true;
//                                L.i("001");
                            } else if (i == R.id.erb_girl) {
                                tempGender = false;
//                                L.i("002");
                            }

                        }
                    });

                    user.setSex(tempGender);

                    if (!TextUtils.isEmpty(desc)) {
                        user.setDesc(desc);
                    } else {
                        user.setDesc(getString(R.string.Desc_Is_Empty));
                    }

                    BmobUser bmobUser = BmobUser.getCurrentUser();
                    user.update(bmobUser.getObjectId(), new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null) {
                                btn_update_ok.setVisibility(View.GONE);
                                eRadiogroup.setVisibility(View.GONE);
                                edit_user.setVisibility(View.VISIBLE);
                                et_gender.setText(tempGender ? getString(R.string.gender_male) : getString(R.string.gender_female));
                                et_gender.setVisibility(View.VISIBLE);
                                setEditTextEnabled(false);
                                Toast.makeText(getActivity(), getString(R.string.succeed), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), getString(R.string.error) + e.getErrorCode(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(getActivity(), getString(R.string.toast_not_empty), Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.sign_out:
                MyUser.logOut();
                BmobUser currentUser = MyUser.getCurrentUser();
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
                break;
        }
    }

}
