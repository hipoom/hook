package com.hipoom.hook;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author ZhengHaiPeng
 * @since 2024/7/28 17:16
 */
public interface ParamsTransformer {

    /* ======================================================= */
    /* Fields                                                  */
    /* ======================================================= */

    ParamsTransformer defaultImpl = (type, value) -> {
        // 如果是数字，返回数字
        if (ReflectUtils.isSubClassOf(type, Number.class)) {
            return value == null ? type.getSimpleName() : value.toString();
        }

        // 如果是字符串，返回字符串本身
        if (ReflectUtils.isSubClassOf(type, String.class)) {
            if (value == null) {
                return "(String) null";
            }
            return  "\"" + value + "\"";
        }

        // 如果是 boolean 类型
        if (ReflectUtils.isSubClassOf(type, boolean.class) || ReflectUtils.isSubClassOf(type, Boolean.class)) {
            if (value == null) {
                return "(Boolean) null";
            }
            return String.valueOf(value);
        }

        // 如果是 Class 类型
        if (ReflectUtils.isSubClassOf(type, Class.class)) {
            if (value == null) {
                return "(Class) null";
            }
            return type.getName();
        }

        // 否则返回类名
        return type.getSimpleName();
    };



    /* ======================================================= */
    /* Public Methods                                          */
    /* ======================================================= */

    /**
     * 将类型为 type 的参数 value 转为需要打印的文本.
     */
    @Nullable
    String transform(@NonNull Class<?> type, @Nullable Object value);

}
