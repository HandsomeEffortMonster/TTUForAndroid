package com.lzh.Bean;

/**
 * Created by LZH on 2018/6/26.
 */

public class UserBean {
    private String Uname;
    private int Uage;

    public UserBean(){

    }

    public UserBean(String uname,int uage){
        this.Uname = uname;
        this.Uage = uage;
    }

    public String getUname() {
        return Uname;
    }

    public void setUname(String uname) {
        Uname = uname;
    }

    public int getUage() {
        return Uage;
    }

    public void setUage(int uage) {
        Uage = uage;
    }
}
