package com.hipoom.hook;

import androidx.annotation.NonNull;
import com.hipoom.hook.adapter.FrameContext;

/**
 * @author ZhengHaiPeng
 * @since 2024/7/28 16:47
 */
public interface HookAction {

    void onAction(@NonNull FrameContext context);

}
