package com.hipoom.hook;

import java.lang.reflect.Field;

/**
 * @author ZhengHaiPeng
 * @since 6/25/23 10:56 AM
 */
public class ReflectUtils {

    /* ======================================================= */
    /* Public Methods                                          */
    /* ======================================================= */

    /**
     * 设置 [c] 类的 [fieldName] 字段的值为 [value].
     */
    public static void setStaticBoolean(Class<?> c, String fieldName, boolean value) {
        try {
            Field field = c.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(null, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 判断 a 是否为 b 的子类.
     */
    public static boolean isSubClassOf(Class<?> a, Class<?> b) {
        return (b.isAssignableFrom(a));
    }
}
