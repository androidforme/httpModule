package com.zkzz.module.http.net;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.text.TextUtils;

import com.zkzz.module.base.utils.LogUtils;
import com.zkzz.module.base.utils.ToastUtils;
import com.zkzz.module.http.R;
import com.zkzz.module.http.model.BaseRespondModel;
import com.zkzz.module.http.view.LoadingDialog;
import com.zkzz.module.http.view.LoadingDialogManager;

import java.net.ConnectException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 自定义的观察者，方便处理自己APP的中通用处理逻辑
 * <p>
 * 2017-01-17
 *
 * @author WuMeng
 * @version 1.0
 */
public class RetrofitObserver<T> implements Observer<BaseRespondModel<T>> {

    private LoadingDialog mLoadingDialog;
    private Context mContext;
    private Disposable mDisposable;

    /**
     * 不显示加载框的请求
     */
    public RetrofitObserver() {

    }

    /**
     * 显示加载框的请求
     *
     * @param context
     */
    public RetrofitObserver(Context context) {
        this.mContext = context;
    }


    // ----------------- Observer -----------------

    @Override
    public void onSubscribe(Disposable disposable) {
        this.mDisposable = disposable;
        // 回调自己的业务逻辑
        onStart();

        if (null != mContext) {
            if (null == mLoadingDialog) {
                mLoadingDialog = new LoadingDialog(mContext).builder();
                mLoadingDialog.setOnDismissListener(mOnDismissListener);
            }
            mLoadingDialog.show();
        }
    }

    @Override
    public void onNext(BaseRespondModel<T> value) {
        if (null != value && ErrorCodes.SUCCESS == value.code) {
            // 响应不为空且解析出成功码 - 不考虑数据体（Data）为空，比如一些删除操作无需返回Data
            onSuccess(value.data);
        } else {
            ToastUtils.showShort(TextUtils.isEmpty(value.msg) ? value.message : value.msg);
            onFail(value.code, TextUtils.isEmpty(value.msg) ? value.message : value.msg);
        }
    }

    @Override
    public void onError(Throwable e) {
        LogUtils.e(e);
        if (e instanceof ConnectException) {
            // 网络连接错误，请检查网络。
            ToastUtils.showShort(R.string.base_http_network_error);
        }
        //弹窗不为空则关闭弹窗
        if (null != mLoadingDialog) {
            LoadingDialogManager.getLDManager().dismissLoadingDialog(mLoadingDialog);
        }
        onFail(ErrorCodes.FAILURE, e.toString());
        // 请求失败后不会执行onComplete，所以需要回调自己的业务逻辑
        onFinish();
    }

    @Override
    public void onComplete() {
        //弹窗不为空则关闭弹窗
        if (null != mLoadingDialog) {
            LoadingDialogManager.getLDManager().dismissLoadingDialog(mLoadingDialog);
        }
        // 回调自己的业务逻辑
        onFinish();
    }

    /**
     * 弹窗被关闭后取消请求
     */
    private OnDismissListener mOnDismissListener = new OnDismissListener() {

        @Override
        public void onDismiss(DialogInterface dialogInterface) {
            dispose();
        }
    };

    /**
     * 取消请求
     */
    public void dispose() {
        if (null != mDisposable) {
            mDisposable.dispose();
        }
    }

    // ----------------- 执行自己的业务 -----------------

    /**
     * 执行自己的业务逻辑 - 请求成功
     */
    public void onSuccess(T t) {

    }

    /**
     * 执行自己的业务逻辑 - 请求开始
     */
    public void onStart() {

    }

    /**
     * 执行自己的业务逻辑 - 请求错误
     *
     * @param code    - 搞不懂服务器为什么CODE设计为字符串
     * @param message
     */
    public void onFail(int code, String message) {

    }

    /**
     * 执行自己的业务逻辑 - 请求结束
     */
    public void onFinish() {

    }
}