package com.hipoom.processor;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import com.hipoom.hook.JavaHook;
import com.hipoom.hook.adapter.pine.PineInitializer;

@TestAnnotation
public class MainActivity extends AppCompatActivity implements TestInterface {

    public static class TestClass {

        TestClass() {}

        TestClass(String name) {}

        public void objFun() {

        }

        public void objFun(String name) {

        }

        public static void staticMethod() {

        }

        public static void staticMethod(String name) {

        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PineInitializer.init();

        //JavaHook.forClass(TestClass.class).constructor.all()
        //    .doBefore(context -> {
        //        Log.i("ZHP_TEST", "TestClass 的构造函数执行了.");
        //    })
        //    .hook();

        //JavaHook.forClass(TestClass.class).constructor.matchParamsTypes(String.class)
        //    .logWhenCall(false)
        //    .doAfter(ctx -> {
        //        Log.i("ZHP_TEST", "TestClass() 的构造函数执行完毕了.");
        //    })
        //    .hook();

        //JavaHook.forClass(TestClass.class).method.all().logWhenCall(false).hook();
        JavaHook.forClass(TestClass.class).method.named("objFun").matchParamsTypes(String.class).logWhenCall(false).hook();
        JavaHook.forClass(TestClass.class).method.named("staticMethod").all().logWhenCall(false).hook();

        TestClass a = new TestClass();
        new TestClass("abc");

        a.objFun("abc");
        a.objFun();

        TestClass.staticMethod();
        TestClass.staticMethod("def");
    }
}