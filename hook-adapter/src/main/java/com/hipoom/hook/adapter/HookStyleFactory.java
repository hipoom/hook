package com.hipoom.hook.adapter;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 由于 hook 的实现多种多样，这个工厂类管理这些实现。
 *
 * @author ZhengHaiPeng
 * @since 2024/7/28 14:25
 */
public class HookStyleFactory {

    /* ======================================================= */
    /* Fields                                                  */
    /* ======================================================= */

    private static final Map<String, IHookStyle> styles = new HashMap<>();



    /* ======================================================= */
    /* Public Methods                                          */
    /* ======================================================= */

    /**
     * 注册一种 hook 方式。
     */
    public static synchronized void registry(@NonNull String name, @NonNull IHookStyle hook) {
        styles.put(name, hook);
    }

    /**
     * 获取指定名称的 hook 方式。
     */
    @Nullable
    public static synchronized IHookStyle get(@NonNull String name) {
        return styles.get(name);
    }

    /**
     * 获取任意一种 hook 方式。
     */
    @Nullable
    public static synchronized IHookStyle getAnyOne() {
        Collection<IHookStyle> temp = styles.values();
        //noinspection ConstantValue
        if (temp == null || temp.isEmpty()) {
            return null;
        }
        return temp.iterator().next();
    }

}
