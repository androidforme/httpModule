package com.zkzz.module.http.view;

import java.util.Stack;

/**
 * 应用程序LoadingDialog管理类：用于LoadingDialog管理和应用程序退出
 * <p>
 * 2015-05-18
 * 
 * @author WuMeng
 * @version 1.0
 */
public class LoadingDialogManager {

	private static Stack<LoadingDialog> loadingDialogStack;
	private static LoadingDialogManager instance;

	private LoadingDialogManager() {
	}

	/**
	 * 单一实例
	 */
	public static LoadingDialogManager getLDManager() {
		if (instance == null) {
			instance = new LoadingDialogManager();
		}
		return instance;
	}

	/**
	 * 添加LoadingDialog到堆栈
	 */
	public void addLoadingDialog(LoadingDialog loadingDialog) {
		if (loadingDialogStack == null) {
			loadingDialogStack = new Stack<LoadingDialog>();
		}
		loadingDialogStack.add(loadingDialog);
	}

	/**
	 * 获取当前LoadingDialog（堆栈中最后一个压入的）
	 */
	public LoadingDialog currentLoadingDialog() {
		LoadingDialog loadingDialog = loadingDialogStack.lastElement();
		return loadingDialog;
	}

	/**
	 * 结束当前LoadingDialog（堆栈中最后一个压入的）
	 */
	public void finishLoadingDialog() {
		LoadingDialog loadingDialog = loadingDialogStack.lastElement();
		dismissLoadingDialog(loadingDialog);
	}

	/**
	 * 结束指定的LoadingDialog
	 */
	public void dismissLoadingDialog(LoadingDialog loadingDialog) {
		if (loadingDialog != null && loadingDialog.isShowing()) {
			loadingDialogStack.remove(loadingDialog);
			loadingDialog.dismiss();
			loadingDialog = null;
		}
	}

	/**
	 * 结束指定类名的LoadingDialog
	 */
	public void finishLoadingDialog(Class<?> cls) {
		for (LoadingDialog loadingDialog : loadingDialogStack) {
			if (loadingDialog.getClass().equals(cls)) {
				dismissLoadingDialog(loadingDialog);
			}
		}
	}

	/**
	 * 结束所有LoadingDialog
	 */
	public void dismissAllLoadingDialog() {
		if (null != loadingDialogStack) {
			for (int i = 0, size = loadingDialogStack.size(); i < size; i++) {
				if (null != loadingDialogStack.get(i) && loadingDialogStack.get(i).isShowing()) {
					loadingDialogStack.get(i).dismiss();
				}
			}
			loadingDialogStack.clear();
		}
	}
}