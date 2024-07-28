package com.hipoom.hook.method;

import androidx.annotation.NonNull;
import com.hipoom.hook.method.AllMethodIntent;
import com.hipoom.hook.method.NamedMethodIntent;

/**
 * @author ZhengHaiPeng
 * @since 2024/7/28 16:54
 */
public class MethodIntent {

   /* ======================================================= */
   /* Fields                                                  */
   /* ======================================================= */

   private final Class<?> classObj;



   /* ======================================================= */
   /* Constructors or Instance Creator                        */
   /* ======================================================= */

   public MethodIntent(Class<?> classObj) {
      this.classObj = classObj;
   }



   /* ======================================================= */
   /* Public Methods                                          */
   /* ======================================================= */

   @NonNull
   public AllMethodIntent all() {
      return new AllMethodIntent(classObj);
   }

   @NonNull
   public NamedMethodIntent named(@NonNull String name) {
      return new NamedMethodIntent(classObj, name);
   }

}
