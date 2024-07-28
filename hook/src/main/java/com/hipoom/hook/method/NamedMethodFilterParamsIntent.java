package com.hipoom.hook.method;

import androidx.annotation.NonNull;
import com.hipoom.hook.AbsIntent;
import com.hipoom.hook.adapter.ClassHolder;
import com.hipoom.hook.adapter.HookCallback;
import com.hipoom.hook.adapter.HookParams;
import com.hipoom.hook.adapter.IHookStyle;

/**
 * @author ZhengHaiPeng
 * @since 2024/7/28 17:02
 */
public class NamedMethodFilterParamsIntent extends AbsIntent {

    /* ======================================================= */
    /* Fields                                                  */
    /* ======================================================= */

    Class<?>[] types;

    String methodName;



    /* ======================================================= */
    /* Constructors or Instance Creator                        */
    /* ======================================================= */

    public NamedMethodFilterParamsIntent(
        @NonNull Class<?> needHookClass,
        @NonNull String methodName,
        @NonNull Class<?>[] types
    ) {
        super(needHookClass);
        this.methodName = methodName;
        this.types = types;
    }



    /* ======================================================= */
    /* Public Methods                                          */
    /* ======================================================= */

    @Override
    protected void onHook(@NonNull IHookStyle hooker) {
        hooker.hook(new HookParams() {
            @NonNull
            @Override
            public ClassHolder getHookClass() {
                return new ClassHolder(needHookClass);
            }

            @NonNull
            @Override
            public String getMethodName() {
                return methodName;
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
