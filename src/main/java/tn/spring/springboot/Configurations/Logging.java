package tn.spring.springboot.Configurations;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Aspect for logging and performance monitoring.
 */
@Component
@Aspect
@Slf4j
public class Logging{

    /**
     * Logs a message after calculating the amount payable by the client.
     */
    @AfterReturning("execution(* tn.spring.springboot.Services.Implementation.*.calculateAmountToPayByClient*(..))")
    public void logAfterAmountCalculation(JoinPoint joinPoint) {
        log.info("******* Invoice amount calculated successfully *************");
    }

    /**
     * Logs a message after the execution of methods that start with 'get'.
     */
    @After("execution(* tn.spring.springboot.Services.Implementation.*.get*(..))")
    public void logAfterGetMethods(JoinPoint joinPoint) {
        log.info("******* After executing the method starting with 'get': "
                + joinPoint.getSignature().getName());
    }

    /**
     * Calculates and logs the execution time of any method in the Implementation package.
     */
    @Around("execution(* tn.spring.springboot.Services.Implementation.*.*(..))")
    public Object monitorPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long elapsedTime = System.currentTimeMillis() - startTime;
        log.info("Method name: " + joinPoint.getSignature().getName());
        log.info("Execution time: " + elapsedTime + " milliseconds.");
        return result;
    }

    /**
     * Logs method entry for all methods in the Controllers package.
     */
    @After("execution(* tn.spring.springboot.Controllers.*.*(..))")
    public void logControllerMethodEntry(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        log.debug("Entering method: " + methodName);
    }

    /**
     * Logs a message after executing methods that take a String parameter.
     */
    @After("execution(* tn.spring.springboot.Services.Implementation.*.*(String))")
    public void logAfterStringParameterMethods(JoinPoint joinPoint) {
        log.info("******* After executing the method with a String parameter: "
                + joinPoint.getSignature().getName());
    }
}
