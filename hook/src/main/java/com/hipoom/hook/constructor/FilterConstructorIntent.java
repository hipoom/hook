package com.hipoom.hook.constructor;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import androidx.annotation.NonNull;
import com.hipoom.hook.AbsIntent;
import com.hipoom.hook.adapter.ClassHolder;
import com.hipoom.hook.adapter.HookCallback;
import com.hipoom.hook.adapter.HookParams;
import com.hipoom.hook.adapter.IHookStyle;

/**
 * @author ZhengHaiPeng
 * @since 2024/7/28 16:52
 */
public class FilterConstructorIntent extends AbsIntent {

    /* ======================================================= */
    /* Fields                                                  */
    /* ======================================================= */

    Class<?>[] types;



    /* ======================================================= */
    /* Constructors or Instance Creator                        */
    /* ======================================================= */

    public FilterConstructorIntent(Class<?> needHookClass, Class<?>[] types) {
        super(needHookClass);
        this.types = types;
    }



    /* ======================================================= */
    /* Public Methods                                          */
    /* ======================================================= */

    public void onHook(@NonNull IHookStyle hooker) {
        hooker.hook(new HookParams() {
            @NonNull
            @Override
            public ClassHolder getHookClass() {
                return new ClassHolder(needHookClass);
            }

            @NonNull
            @Override
            public String getMethodName() {
                return HookParams.METHOD_NAME_CONSTRUCTOR;
            }

            @NonNull
            @Override
            public Class<?>[] getParametersType() {
                return types;
            }

            @NonNull
            @Override
            public HookCallback getHookCallback() {
                return actionsHolder.getHookCallback();
            }
        });
    }
}
