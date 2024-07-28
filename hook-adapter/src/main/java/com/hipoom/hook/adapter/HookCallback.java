package com.hipoom.hook.adapter;

import androidx.annotation.NonNull;

/**
 * @author ZhengHaiPeng
 * @since 2024/7/28 15:16
 */
public interface HookCallback {

    /* ======================================================= */
    /* Public Methods                                          */
    /* ======================================================= */

    void doBefore(@NonNull FrameContext context);

    void doAfter(@NonNull FrameContext context);

}
