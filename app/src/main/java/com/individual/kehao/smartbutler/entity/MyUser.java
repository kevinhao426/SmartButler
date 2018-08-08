package com.individual.kehao.smartbutler.entity;

import cn.bmob.v3.BmobUser;

/*
 * Project Name: SmartButler
 * Package Name: com.individual.kehao.smartbutler.entity
 * File    Name: MyUser
 * Create  By:   Ke Hao
 * Create  Time: 2018/7/31
 * Description :
 */
public class MyUser extends BmobUser {

    private int age;
    private boolean sex;
    private String desc;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
