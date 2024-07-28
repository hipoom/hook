package com.hipoom.hook;

import com.hipoom.hook.adapter.ClassHolder;
import com.hipoom.hook.constructor.ConstructorIntent;

/**
 * @author ZhengHaiPeng
 * @since 2024/7/28 16:22
 */
public class ClassIntent {

    /* ======================================================= */
    /* Fields                                                  */
    /* ======================================================= */

    public final ConstructorIntent constructor;

    public final MethodIntent method;



    /* ======================================================= */
    /* Constructors or Instance Creator                        */
    /* ======================================================= */

    public ClassIntent(ClassHolder classHolder) {
        constructor = new ConstructorIntent(classHolder.getClassObj());
        method = new MethodIntent(classHolder.getClassObj());
    }
}
