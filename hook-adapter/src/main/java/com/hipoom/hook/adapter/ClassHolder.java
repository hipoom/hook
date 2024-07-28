package com.hipoom.hook.adapter;

/**
 * @author ZhengHaiPeng
 * @since 2024/7/28 15:48
 */
public class ClassHolder {

    /* ======================================================= */
    /* Fields                                                  */
    /* ======================================================= */

    private Class<?> classObj;

    private String className;



    /* ======================================================= */
    /* Constructors or Instance Creator                        */
    /* ======================================================= */

    public ClassHolder(String className) {
        this.className = className;
        try {
            this.classObj = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ClassHolder(Class<?> klass) {
        this.classObj = klass;
        this.className = klass.getName();
    }



    /* ======================================================= */
    /* Public Methods                                          */
    /* ======================================================= */

    public Class<?> getClassObj() {
        return classObj;
    }

    public String getClassName() {
        return className;
    }
}
