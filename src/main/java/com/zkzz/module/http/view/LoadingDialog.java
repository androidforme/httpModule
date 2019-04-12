package com.zkzz.module.http.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnDismissListener;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.zkzz.module.base.utils.LogUtils;
import com.zkzz.module.base.utils.ToastUtils;
import com.zkzz.module.http.R;


/**
 * 封装的统一弹窗
 * <p>
 * 2015-06-27
 *
 * @author WuMeng
 * @version 1.0
 */
public class LoadingDialog {

    private Context context;
    private Dialog dialog;
    private TextView promptText;
    private CountDownTimer mCountDownTimer;

    public LoadingDialog(Context context) {
        this.context = context;
    }

    public LoadingDialog builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(R.layout.base_http_view_loading, null);
        promptText = (TextView) view.findViewById(R.id.promptText);
        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.BaseHttpLoadingDialogStyle);
        dialog.setContentView(view);
        return this;
    }

    public boolean isShowing() {
        return dialog.isShowing();
    }

    public void show() {
        try {
            dialog.show();
            LoadingDialogManager.getLDManager().addLoadingDialog(this);
            // 如果设置了超时则开启
            if (null != mCountDownTimer) {
                mCountDownTimer.start();
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    /**
     * 点击外部消失
     *
     * @param cancel
     */
    public void setCanceledOnTouchOutside(boolean cancel) {
        dialog.setCanceledOnTouchOutside(cancel);
    }

    /**
     * 设置超时消失时间
     *
     * @param timeout 超时时间 - 秒
     */
    public void setTimeout(int timeout) {
        mCountDownTimer = new CountDownTimer(timeout * 1000, timeout * 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                ToastUtils.showShort(R.string.base_http_network_timeout_error);
                dismiss();
            }
        };
    }

    public void setPrompt(String prompt) {
        if (null != dialog && null != promptText && !TextUtils.isEmpty(prompt)) {
            promptText.setText(prompt);
        }
    }

    public void dismiss() {
        try {
            if (null != dialog && dialog.isShowing()) {
                // 如果设置了超时则取消
                if (null != mCountDownTimer) {
                    mCountDownTimer.cancel();
                }
                dialog.dismiss();
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public void setOnDismissListener(OnDismissListener onDismissListener) {
        dialog.setOnDismissListener(onDismissListener);
    }
}
