package com.hipoom.hook.method;

import java.lang.reflect.Method;

import androidx.annotation.NonNull;
import com.hipoom.hook.AbsIntent;
import com.hipoom.hook.adapter.ClassHolder;
import com.hipoom.hook.adapter.HookCallback;
import com.hipoom.hook.adapter.HookParams;
import com.hipoom.hook.adapter.IHookStyle;

/**
 * 类中指定名字的所有函数都 hook.
 *
 * @author ZhengHaiPeng
 * @since 2024/7/28 17:02
 */
public class NamedMethodAllIntent extends AbsIntent {

    /* ======================================================= */
    /* Fields                                                  */
    /* ======================================================= */

    String methodName;



    /* ======================================================= */
    /* Constructors or Instance Creator                        */
    /* ======================================================= */

    public NamedMethodAllIntent(
        @NonNull Class<?> needHookClass,
        @NonNull String methodName
    ) {
        super(needHookClass);
        this.methodName = methodName;
    }



    /* ======================================================= */
    /* Public Methods                                          */
    /* ======================================================= */

    @Override
    protected void onHook(@NonNull IHookStyle hooker) {
        Method[] methods = needHookClass.getDeclaredMethods();
        for (Method method : methods) {
            if (!methodName.equals(method.getName())) {
                continue;
            }

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
