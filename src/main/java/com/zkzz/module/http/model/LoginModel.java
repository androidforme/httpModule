package com.zkzz.module.http.model;

import com.google.gson.annotations.SerializedName;

/**
 * 登录返回的模型
 */
public class LoginModel {

    @SerializedName("refresh_token")
    public String refreshToken;

    @SerializedName("token_type")
    public String tokenType;

    public String scope;

    @SerializedName("expires_in")
    public int expiresIn;

    @SerializedName("access_token")
    public String accessToken;

}
