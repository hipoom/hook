package com.hipoom.hook.method;

/**
 * @author ZhengHaiPeng
 * @since 2024/7/28 16:56
 */
public class NamedMethodIntent {

   /* ======================================================= */
   /* Fields                                                  */
   /* ======================================================= */

   private final Class<?> needHookClass;
   private final String   name;



   /* ======================================================= */
   /* Constructors or Instance Creator                        */
   /* ======================================================= */

   public NamedMethodIntent(Class<?> classObj, String name) {
      this.needHookClass = classObj;
      this.name = name;
   }



   /* ======================================================= */
   /* Public Methods                                          */
   /* ======================================================= */

   public NamedMethodFilterParamsIntent matchParamsTypes(Class<?>... types) {
      return new NamedMethodFilterParamsIntent(needHookClass, name, types);
   }

   public NamedMethodAllIntent all() {
      return new NamedMethodAllIntent(needHookClass, name);
   }

}
