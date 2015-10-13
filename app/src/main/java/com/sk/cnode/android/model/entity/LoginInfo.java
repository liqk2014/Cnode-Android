package com.sk.cnode.android.model.entity;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

public class LoginInfo {

    private String id;

    @SerializedName("loginname")
    private String loginName;

    @SerializedName("avatar_url")
    private String avatarUrl;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getAvatarUrl() { // TODO 修复头像地址的历史遗留问题
        if (!TextUtils.isEmpty(avatarUrl) && avatarUrl.startsWith("//gravatar.com/avatar/")) {
            return "https:" + avatarUrl;
        } else {
            return avatarUrl;
        }
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

}
