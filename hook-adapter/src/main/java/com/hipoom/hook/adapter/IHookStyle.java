package com.hipoom.hook.adapter;

import androidx.annotation.NonNull;

/**
 * Hook 方式的接口。
 * epic、 pine 等等框架都可以实现这个接口。
 *
 * @author ZhengHaiPeng
 * @since 2024/7/28 14:16
 */
public interface IHookStyle {

    /* ======================================================= */
    /* Public Methods                                          */
    /* ======================================================= */

    void hook(@NonNull HookParams options);

}
