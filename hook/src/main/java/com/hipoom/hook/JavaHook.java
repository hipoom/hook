package com.hipoom.hook;

import androidx.annotation.NonNull;
import com.hipoom.hook.adapter.ClassHolder;

/**
 * @author ZhengHaiPeng
 * @since 2024/7/28 16:21
 */
public class JavaHook {

    /* ======================================================= */
    /* Public Methods                                          */
    /* ======================================================= */

    public static ClassIntent forClass(@NonNull Class<?> classObj) {
        return new ClassIntent(new ClassHolder(classObj));
    }

    public static ClassIntent forClass(@NonNull String className) {
        return new ClassIntent(new ClassHolder(className));
    }

}
