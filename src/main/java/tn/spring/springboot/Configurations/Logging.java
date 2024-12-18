package tn.spring.springboot.Configurations;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Aspect for logging and performance monitoring.
 */
@Component
@Aspect
@Slf4j
public class Logging {

    @AfterReturning("execution(* tn.spring.springboot.Services.Implementation.*.ajouter*(..))")
    public void logMethodEntry(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        log.info("Gestion match : Ajout réalisé avec succès" + " dans la méthode : " + name);

    }
}
