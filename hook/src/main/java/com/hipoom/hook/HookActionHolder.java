package com.hipoom.hook;

import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.Arrays;

import android.util.Log;
import androidx.annotation.NonNull;
import com.hipoom.hook.adapter.FrameContext;
import kotlin.collections.ArraysKt;
import com.hipoom.hook.adapter.HookCallback;

/**
 * @author ZhengHaiPeng
 * @since 2024/7/28 16:49
 */
public class HookActionHolder {

    /* ======================================================= */
    /* Fields                                                  */
    /* ======================================================= */

    Class<?> needHookClass;

    HookAction onBeforeAction;
    HookAction onAfterAction;
    HookAction logWhenCallAction;



    /* ======================================================= */
    /* Constructors or Instance Creator                        */
    /* ======================================================= */

    public HookActionHolder(Class<?> needHookClass) {
        this.needHookClass = needHookClass;
    }



    /* ======================================================= */
    /* Public Methods                                          */
    /* ======================================================= */

    public void doBefore(@NonNull HookAction action) {
        onBeforeAction = action;
    }

    public void doAfter(@NonNull HookAction action) {
        onAfterAction = action;
    }

    public void logWhenCall(boolean needPrintTrace) {
        logWhenCallAction = context -> {
            Member member = context.getMember();

            String methodName = member.getName();
            Class<?> classObj = member.getDeclaringClass();

            Object[] args = context.getArgs();

            // 拼接参数类型
            StringBuilder sb = new StringBuilder();
            Class<?>[] types = null;
            if (member instanceof Method) {
                types = ((Method) member).getParameterTypes();
            } else if (member instanceof Constructor) {
                types = ((Constructor<?>) member).getParameterTypes();
            } else {
                throw new IllegalStateException("未知的 Member 类型.");
            }

            for (int i = 0; i < types.length; i++) {
                if (args == null || args.length <= i) {
                    sb.append("null, ");
                } else {
                    String des = ParamsTransformer.defaultImpl.transform(types[i], args[i]);
                    sb.append(des).append(", ");
                }
            }

            // 去掉末尾的「, 」
            if (sb.toString().endsWith(", ")) {
                sb.deleteCharAt(sb.length() - 1);
                sb.deleteCharAt(sb.length() - 1);
            }

            // 打印日志
            String msg = needHookClass.getSimpleName() + "#" + methodName + "(" + sb + ")" + " 被执行了";
            if (needPrintTrace) {
                String trace = dumpTrace(8);
                Log.i("JavaHook", msg + "，调用堆栈是: \n" + trace);
            } else {
                Log.i("JavaHook", msg);
            }
        };
    }

    @NonNull
    public HookCallback getHookCallback() {
        return new HookCallback() {
            @Override
            public void doBefore(@NonNull FrameContext context) {
                if (logWhenCallAction != null) {
                    logWhenCallAction.onAction(context);
                }
                if (onBeforeAction != null) {
                    onBeforeAction.onAction(context);
                }
            }

            @Override
            public void doAfter(@NonNull FrameContext context) {
                if (onAfterAction != null) {
                    onAfterAction.onAction(context);
                }
            }
        };
    }



    /**
     * 返回堆栈信息。
     */
    public static String dumpTrace(int depth) {
        String[] traces = Log.getStackTraceString(new Throwable()).split("\n");
        traces = Arrays.copyOfRange(traces, depth, traces.length);
        return ArraysKt.joinToString(traces, "\n", "", "", Integer.MAX_VALUE, "", s -> s);
    }
}
