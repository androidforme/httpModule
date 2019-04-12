package com.zkzz.module.http.interceptor;

import android.util.Base64;

import com.zkzz.module.base.utils.LogUtils;
import com.zkzz.module.http.utils.LoginUtils;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 基础拦截器，用于封装通用参数
 * <p>
 * 2017-01-17
 *
 * @author WuMeng
 * @version 1.0
 */
public class BaseInterceptor implements Interceptor {

    private final String APP_KEY = "1HiJn51i47ylFfho3VBubKIMnUguHISHgfujC66V";
    private final String SECRET_KEY = "DmcVBU5YzW8Qy1qr14TeRrKNQHLYRG4gpYzoSwW65cSJdusr6SupbWG5g0KlGKuZKRhB8cTAVDVeE6s8Ykx3tOxSr038DapOSsYUF7IHupqqXkT8Hx2XoD6RGz16Z2yN";

    // 服务器三番五次出问题，暂时写死
    private final String AUTH = "Basic MUhpSm41MWk0N3lsRmZobzNWQnViS0lNblVndUhJU0hnZnVqQzY2VjpEbWNWQlU1WXpXOFF5MXFyMTRUZVJyS05RSExZUkc0Z3BZem9Td1c2NWNTSmR1c3I2U3VwYldHNWcwS2xHS3VaS1JoQjhjVEFWRFZlRTZzOFlreDN0T3hTcjAzOERhcE9Tc1lVRjdJSHVwcXFYa1Q4SHgyWG9ENlJHejE2WjJ5Tg==";

    @Override
    public Response intercept(Chain chain) throws IOException {

        String auth = LoginUtils.isLogined() ? LoginUtils.getLoginInfo().tokenType + " " + LoginUtils.getLoginInfo().accessToken : AUTH;
        LogUtils.d("isLogined " + LoginUtils.isLogined());
        LogUtils.d(auth);

        // 添加公共请求头
        Request request = chain.request().newBuilder()
                // Authorization => 令牌（String，格式形如Bearer {token}）
                .addHeader("Authorization", auth)
                .build();

        // 解析服务器响应头
        Response response = chain.proceed(request);
//        // 解析并保存认证信息(不为空才保存)
//        String authorization = response.header("Authorization");
//        if (StringUtils.isNotEmpty(authorization)) {
//            NetSharedPref.setAuthorization(authorization);
//        }

        return response;
    }


    /**
     * 构造Basic Auth认证头信息
     *
     * @return
     */
    private String getBasicAuth() {
        String auth = APP_KEY + ":" + SECRET_KEY;
        byte[] encodedAuth = Base64.encode(auth.getBytes(Charset.forName("UTF-8")), Base64.DEFAULT);
        String authHeader = "Basic " + new String(encodedAuth);
        LogUtils.i("authHeader is: " + authHeader);
        return authHeader;
    }

}
