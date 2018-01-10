package com.example.ricardo.tickit2.data.entity;

import com.example.ricardo.tickit2.data.model.User;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Ricardo on 2017/11/30.
 */

@Entity
public class GDUser {
    @Id
    private String id;
    private String nickName;
    private String realName;
    private String mobileNumber;
    private String password;
    private String avatar;
    private boolean isSuperUser;
    private boolean isAdmin;
    private int vipPoint;
    @Generated(hash = 581695833)
    public GDUser(String id, String nickName, String realName, String mobileNumber,
            String password, String avatar, boolean isSuperUser, boolean isAdmin,
            int vipPoint) {
        this.id = id;
        this.nickName = nickName;
        this.realName = realName;
        this.mobileNumber = mobileNumber;
        this.password = password;
        this.avatar = avatar;
        this.isSuperUser = isSuperUser;
        this.isAdmin = isAdmin;
        this.vipPoint = vipPoint;
    }
    @Generated(hash = 1014226889)
    public GDUser() {
    }


    public GDUser(User user){
        this.id = user.getId();
        this.nickName = user.getNickName();
        this.realName = user.getRealName();
        this.mobileNumber = user.getMobileNumber();
        this.password = user.getPassword();
        this.avatar = user.getAvatar();
        this.isAdmin = user.isAdmin();
        this.isSuperUser = user.isSuperUser();
        this.vipPoint = user.getVipPoint();
    }

    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNickName() {
        return this.nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getRealName() {
        return this.realName;
    }
    public void setRealName(String realName) {
        this.realName = realName;
    }
    public String getMobileNumber() {
        return this.mobileNumber;
    }
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getAvatar() {
        return this.avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public boolean getIsSuperUser() {
        return this.isSuperUser;
    }
    public void setIsSuperUser(boolean isSuperUser) {
        this.isSuperUser = isSuperUser;
    }
    public boolean getIsAdmin() {
        return this.isAdmin;
    }
    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    public int getVipPoint() {
        return this.vipPoint;
    }
    public void setVipPoint(int vipPoint) {
        this.vipPoint = vipPoint;
    }
}
