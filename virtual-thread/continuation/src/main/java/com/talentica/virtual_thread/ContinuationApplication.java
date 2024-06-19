package com.talentica.virtual_thread;

import jdk.internal.vm.Continuation;
import jdk.internal.vm.ContinuationScope;


//--add-exports java.base/jdk.internal.vm=ALL-UNNAMED //javac options
// --enable-preview --add-exports java.base/jdk.internal.vm=ALL-UNNAMED //vm option
public class ContinuationApplication {

    public static void main(String[] args) {
        ContinuationScope scope = new ContinuationScope("main");
        var continuation = new Continuation(scope, () -> {
            System.out.println("Hello");
            Continuation.yield(scope);
            System.out.println("World");
        });

        System.out.println("Before");
        continuation.run();
        System.out.println("After");
        continuation.run();
    }
}

