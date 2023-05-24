package org.example.proxy.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.SimpleDateFormat;
import java.util.Date;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface Log {
    String dateFormat() default "yyyy-MM-dd HH:mm:ss";

    String value() default "";
}

interface Calculator {
    int add(int a, int b);

    int subtract(int a, int b);
}

class CalculatorImpl implements Calculator {
    @Override
    @Log
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    @Log(value = "INFO", dateFormat = "yyyy年MM月dd日 HH时mm分ss秒")
    public int subtract(int a, int b) {
        return a - b;
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
        Method targetMethod = target.getClass().getMethod(method.getName(), method.getParameterTypes());
        if (targetMethod.isAnnotationPresent(Log.class)) {
            Log annotation = targetMethod.getAnnotation(Log.class);
            String value = annotation.value();
            if ("INFO".equals(value)) {
                System.out.println("\u001B[44;30m" + new SimpleDateFormat(annotation.dateFormat()).format(new Date()) + "Before method invocation:  INFO: \u001B[0m" + "\u001B[45;30m " + method.getName() + "() \u001B[0m");
            }
        }
        Object result = method.invoke(target, args);
        return result;
    }
}

public class AnnotationProxyLogExample {
    public static void main(String[] args) {
        Calculator target = new CalculatorImpl();
        CalculatorProxy proxy = new CalculatorProxy();
        Calculator calculator = (Calculator) proxy.bind(target);

        int sum = calculator.add(3, 5);
        System.out.println("Sum: " + sum);

        int difference = calculator.subtract(7, 4);
        System.out.println("Difference: " + difference);
    }
}
