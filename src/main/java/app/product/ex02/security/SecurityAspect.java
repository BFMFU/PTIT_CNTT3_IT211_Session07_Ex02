package app.product.ex02.security;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SecurityAspect {
    private String currentUserRole = "VIP";

    @Before("execution(* *..*Service.add*(..))")
    public void verifyUser(JoinPoint joinPoint) {
        if (!"ADMIN".equals(currentUserRole)) {
            throw new RuntimeException("!! TRUY CẬP BỊ TỪ CHỐI !! User không có quyền.");
        }
    }

}

