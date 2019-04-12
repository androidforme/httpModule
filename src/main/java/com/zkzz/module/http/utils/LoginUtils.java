package com.zkzz.module.http.utils;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.zkzz.module.base.utils.LogUtils;
import com.zkzz.module.base.utils.RemotePreUtils;
import com.zkzz.module.http.model.LoginModel;
import com.zkzz.module.http.model.UserModel;

public class LoginUtils {

    public static void setLoginInfo(LoginModel login) {
        String json = new Gson().toJson(login);
        // 保存Token - 不要用apply，防止未保存成功就进行网络请求，导致无法添加Token
        RemotePreUtils.getServicesPre().edit().putString(RemotePreUtils.LOGIN_JSON, json).commit();
    }

    public static LoginModel getLoginInfo() {
        String login = RemotePreUtils.getServicesPre().getString(RemotePreUtils.LOGIN_JSON, "");
        LogUtils.d(login);
        if (TextUtils.isEmpty(login)) {
            return null;
        } else {
            return new Gson().fromJson(login, LoginModel.class);
        }
    }

    public static boolean isLogined() {
        return null != getLoginInfo() && !TextUtils.isEmpty(getLoginInfo().accessToken);
    }

    public static boolean clear() {
        return RemotePreUtils.getServicesPre().edit().clear().commit();
    }

}
