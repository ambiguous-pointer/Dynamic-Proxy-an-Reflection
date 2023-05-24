package org.example.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Calculator {
    int add(int a, int b);
}

class CalculatorImpl implements Calculator {
    @Override
    public int add(int a, int b) {
        return a + b;
    }
}

class CalculatorProxy implements InvocationHandler {
    private Object target;

    public Object bind(Object target) {
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before method invocation");
        Object result = method.invoke(target, args);
        System.out.println("After method invocation");
        return result;
    }
}

public class DynamicProxyExample {
    public static void main(String[] args) {
        Calculator target = new CalculatorImpl();
        CalculatorProxy proxy = new CalculatorProxy();
        Calculator calculator = (Calculator) proxy.bind(target);
        int result = calculator.add(3, 5);
        System.out.println("Result: " + result);
    }
}
