package com.hipoom.hook;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.annotation.RestrictTo.Scope;
import com.hipoom.hook.adapter.HookStyleFactory;
import com.hipoom.hook.adapter.IHookStyle;

/**
 * @author ZhengHaiPeng
 * @since 2024/7/28 17:42
 */
public abstract class AbsIntent {

    /* ======================================================= */
    /* Fields                                                  */
    /* ======================================================= */

    @RestrictTo(Scope.LIBRARY)
    public Class<?> needHookClass;

    @RestrictTo(Scope.LIBRARY)
    public HookActionHolder actionsHolder;



    /* ======================================================= */
    /* Constructors or Instance Creator                        */
    /* ======================================================= */

    public AbsIntent(Class<?> needHookClass) {
        this.needHookClass = needHookClass;
        actionsHolder = new HookActionHolder(needHookClass);
    }



    /* ======================================================= */
    /* Public Methods                                          */
    /* ======================================================= */


    public AbsIntent doBefore(@NonNull HookAction action) {
        actionsHolder.doBefore(action);
        return this;
    }

    public AbsIntent doAfter(@NonNull HookAction action) {
        actionsHolder.doAfter(action);
        return this;
    }

    public AbsIntent logWhenCall(boolean needPrintTrace) {
        actionsHolder.logWhenCall(needPrintTrace);
        return this;
    }

    public void hook() {
        IHookStyle hooker = HookStyleFactory.getAnyOne();
        if (hooker == null) {
            throw new IllegalStateException("找不到任何 HookStyle 的实现。");
        }

        onHook(hooker);
    }


    protected abstract void onHook(@NonNull IHookStyle hooker);
}
