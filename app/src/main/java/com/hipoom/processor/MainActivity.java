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

        // 初始化 Pine Hook Style
        PineInitializer.init();


    }
}