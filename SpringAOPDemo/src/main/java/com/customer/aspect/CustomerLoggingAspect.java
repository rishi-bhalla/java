package com.customer.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

@Aspect
public class CustomerLoggingAspect {

    @Before("execution(* com.customer.dao.impl.CustomerDAOImpl.addCustomer(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("logBefore() is running!");
        System.out.println("hijacked : " + joinPoint.getSignature().getName());
        System.out.println("******");
    }

    @After("execution(* com.customer.dao.impl.CustomerDAOImpl.addCustomer(..))")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("logAfter() is running!");
        System.out.println("hijacked : " + joinPoint.getSignature().getName());
        System.out.println("******");

    }

    @AfterReturning(
            pointcut = "execution(* com.customer.dao.impl.CustomerDAOImpl.addCustomerReturnValue(..))",
            returning= "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("logAfterReturning() is running!");
        System.out.println("hijacked : " + joinPoint.getSignature().getName());
        System.out.println("Method returned value is : " + result);
        System.out.println("******");
    }

    @AfterThrowing(
            pointcut = "execution(* com.customer.dao.impl.CustomerDAOImpl.addCustomerThrowException(..))",
            throwing= "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {

        System.out.println("logAfterThrowing() is running!");
        System.out.println("hijacked : " + joinPoint.getSignature().getName());
        System.out.println("Exception : " + error);
        System.out.println("******");
    }

    @Around("execution(* com.customer.dao.impl.CustomerDAOImpl.addCustomerAround(..))")
    public void logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("logAround() is running!");
        System.out.println("hijacked method : " + joinPoint.getSignature().getName());
        System.out.println("hijacked arguments : " + Arrays.toString(joinPoint.getArgs()));

        System.out.println("Around before is running!");
        joinPoint.proceed(); //continue on the intercepted method
        System.out.println("Around after is running!");

        System.out.println("******");
    }
}
