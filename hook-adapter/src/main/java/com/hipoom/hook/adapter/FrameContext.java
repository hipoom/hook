package com.hipoom.hook.adapter;

import java.lang.reflect.Member;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author ZhengHaiPeng
 * @since 2024/7/28 15:33
 */
public interface FrameContext {

    /* ======================================================= */
    /* Public Methods                                          */
    /* ======================================================= */

    /**
     * 当前正在执行的对象，可能是个 Method, 也可能是个 Constructor.
     */
    @NonNull
    Member getMember();

    /**
     * this 对象。
     * 如果是静态函数，这里会返回 null.
     */
    @Nullable
    Object getSelf();

    /**
     * 函数执行时传入的参数列表。
     * 可以在调用原函数前修改这个数组的值，达到修改原函数入参的效果。
     */
    @Nullable
    Object[] getArgs();

    /**
     * 函数的返回值。
     */
    @Nullable
    Object getResult();
}
