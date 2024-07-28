package com.hipoom.hook.adapter.pine;

import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.hipoom.hook.adapter.FrameContext;
import com.hipoom.hook.adapter.HookParams;
import com.hipoom.hook.adapter.IHookStyle;
import top.canyie.pine.Pine;
import top.canyie.pine.Pine.CallFrame;
import top.canyie.pine.callback.MethodHook;

/**
 * @author ZhengHaiPeng
 * @since 2024/7/28 15:43
 */
public class PineHookStyle implements IHookStyle {

    /* ======================================================= */
    /* Override/Implements Methods                             */
    /* ======================================================= */
    @Override
    public void hook(@NonNull HookParams options) {
        // 待 hook 的类
        Class<?> classObj = options.getHookClass().getClassObj();

        // 待 hook 的方法
        Set<Member> needHookMembers = generateNeedHookMembers(classObj, options);

        for (Member member : needHookMembers) {
            Pine.hook(member, new MethodHook() {
                @Override
                public void beforeCall(CallFrame callFrame) throws Throwable {
                    super.beforeCall(callFrame);
                    options.getHookCallback().doBefore(convertCallFrame2FrameContext(callFrame));
                }

                @Override
                public void afterCall(CallFrame callFrame) throws Throwable {
                    super.afterCall(callFrame);
                    options.getHookCallback().doAfter(convertCallFrame2FrameContext(callFrame));
                }
            });
        }
    }



    /* ======================================================= */
    /* Private Methods                                         */
    /* ======================================================= */

    /**
     * 计算那些函数需要 hook.
     */
    @NonNull
    private Set<Member> generateNeedHookMembers(@NonNull Class<?> classObj, @NonNull
    HookParams options) {
        String methodName = options.getMethodName();

        // 如果是构造函数
        if (HookParams.METHOD_NAME_CONSTRUCTOR.equals(methodName)) {
            return generateNeedHookMembers4Constructor(classObj, options);
        }
        // 如果是普通的函数
        else {
            return generateNeedHookMembers4Method(classObj, options);
        }
    }

    /**
     * 计算那些构造函数需要 hook.
     */
    @NonNull
    private Set<Member> generateNeedHookMembers4Constructor(@NonNull Class<?> classObj, @NonNull
    HookParams options) {
        Class<?>[] types = options.getParametersType();

        // 如果是要 hook 所有构造函数
        if (HookParams.PARAMS_TYPE_ALL == types) {
            Constructor<?>[] constructors = classObj.getDeclaredConstructors();
            return new HashSet<>(Arrays.asList(constructors));
        }

        // 如果只想 hook 指定参数列表的构造函数
        try {
            Constructor<?> constructor = classObj.getDeclaredConstructor(types);
            Set<Member> res = new HashSet<>();
            res.add(constructor);
            return res;
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 计算那些普通函数需要 hook.
     */
    @NonNull
    private Set<Member> generateNeedHookMembers4Method(
        @NonNull Class<?> classObj,
        @NonNull HookParams options
    ) {
        Class<?>[] types = options.getParametersType();

        // 如果是要 hook 所有构造函数
        if (HookParams.PARAMS_TYPE_ALL == types) {
            Method[] methods = classObj.getDeclaredMethods();
            return new HashSet<>(Arrays.asList(methods));
        }

        // 如果只想 hook 指定参数列表的构造函数
        try {
            Method method = classObj.getDeclaredMethod(options.getMethodName(), types);
            Set<Member> res = new HashSet<>();
            res.add(method);
            return res;
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @NonNull
    private FrameContext convertCallFrame2FrameContext(@NonNull CallFrame callFrame) {
        return new FrameContext() {
            @NonNull
            @Override
            public Member getMember() {
                return callFrame.method;
            }

            @Nullable
            @Override
            public Object getSelf() {
                return callFrame.thisObject;
            }

            @Nullable
            @Override
            public Object[] getArgs() {
                return callFrame.args;
            }

            @Nullable
            @Override
            public Object getResult() {
                return callFrame.getResult();
            }
        };
    }
}
