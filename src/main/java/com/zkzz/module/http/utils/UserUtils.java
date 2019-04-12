package com.zkzz.module.http.utils;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.zkzz.module.base.utils.RemotePreUtils;
import com.zkzz.module.http.model.UserModel;

public class UserUtils {

    public static void setUserInfo(UserModel user) {
        String json = new Gson().toJson(user);
        RemotePreUtils.getServicesPre().edit().putString(RemotePreUtils.USER_JSON, json).commit();
    }

    public static UserModel getUserInfo() {
        String json = RemotePreUtils.getServicesPre().getString(RemotePreUtils.USER_JSON, "");
        if (TextUtils.isEmpty(json)) {
            return null;
        } else {
            return new Gson().fromJson(json, UserModel.class);
        }
    }

    public static boolean isHasUser() {
        return null != getUserInfo() && !TextUtils.isEmpty(getUserInfo().telephone);
    }

    public static boolean clear() {
        return RemotePreUtils.getServicesPre().edit().putString(RemotePreUtils.USER_JSON, "").commit();
    }

}
