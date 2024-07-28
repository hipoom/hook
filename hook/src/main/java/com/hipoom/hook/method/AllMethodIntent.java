package com.hipoom.hook.method;

import java.lang.reflect.Method;

import androidx.annotation.NonNull;
import com.hipoom.hook.AbsIntent;
import com.hipoom.hook.adapter.ClassHolder;
import com.hipoom.hook.adapter.HookCallback;
import com.hipoom.hook.adapter.HookParams;
import com.hipoom.hook.adapter.IHookStyle;

/**
 * 类中的所有函数都 hook.
 *
 * @author ZhengHaiPeng
 * @since 2024/7/28 16:56
 */
public class AllMethodIntent extends AbsIntent {

    /* ======================================================= */
    /* Constructors or Instance Creator                        */
    /* ======================================================= */
    public AllMethodIntent(Class<?> classObj) {
        super(classObj);
    }



    /* ======================================================= */
    /* Override/Implements Methods                             */
    /* ======================================================= */

    @Override
    protected void onHook(@NonNull IHookStyle hooker) {
        Method[] methods = needHookClass.getDeclaredMethods();
        for (Method method : methods) {
            hooker.hook(new HookParams() {
                @NonNull
                @Override
                public ClassHolder getHookClass() {
                    return new ClassHolder(needHookClass);
                }

                @NonNull
                @Override
                public String getMethodName() {
                    return method.getName();
                }

                @NonNull
                @Override
                public Class<?>[] getParametersType() {
                    return method.getParameterTypes();
                }

                @NonNull
                @Override
                public HookCallback getHookCallback() {
                    return actionsHolder.getHookCallback();
                }
            });
        }
    }
}
