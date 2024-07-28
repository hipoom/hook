package com.hipoom.hook.constructor;

/**
 * @author ZhengHaiPeng
 * @since 2024/7/28 16:43
 */
public class ConstructorIntent {

    /* ======================================================= */
    /* Fields                                                  */
    /* ======================================================= */

    Class<?> needHookClass;



    /* ======================================================= */
    /* Constructors or Instance Creator                        */
    /* ======================================================= */

    public ConstructorIntent(Class<?> classObj) {
        needHookClass = classObj;
    }



    /* ======================================================= */
    /* Public Methods                                          */
    /* ======================================================= */

    public AllConstructorIntent all() {
        return new AllConstructorIntent(needHookClass);
    }

    public FilterConstructorIntent matchParamsTypes(Class<?>... types) {
        return new FilterConstructorIntent(needHookClass, types);
    }
}
