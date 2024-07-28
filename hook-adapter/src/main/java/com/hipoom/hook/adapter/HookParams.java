package com.hipoom.hook.adapter;

import androidx.annotation.NonNull;

/**
 * @author ZhengHaiPeng
 * @since 2024/7/28 14:17
 */
public interface HookParams {

    /* ======================================================= */
    /* Fields                                                  */
    /* ======================================================= */

    String METHOD_NAME_CONSTRUCTOR = "<init>";

    Class<?>[] PARAMS_TYPE_ALL = new Class[0];



    /* ======================================================= */
    /* Public Methods                                          */
    /* ======================================================= */

    /**
     * 获取待 hook 的类。
     */
    @NonNull
    ClassHolder getHookClass();

    /**
     * 获取待 hook 的函数名。
     * 如果是构造函数，返回 {@link #METHOD_NAME_CONSTRUCTOR} .
     */
    @NonNull
    String getMethodName();

    /**
     * 获取函数的参数列表。
     * 如果想 hook 所有名字叫 {@link #getMethodName()} 的函数，则返回 {@link #PARAMS_TYPE_ALL}.
     */
    @NonNull
    Class<?>[] getParametersType();

    /**
     * Hook 的回调。
     */
    @NonNull
    HookCallback getHookCallback();

}
