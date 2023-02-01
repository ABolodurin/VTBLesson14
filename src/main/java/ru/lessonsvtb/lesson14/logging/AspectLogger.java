package ru.lessonsvtb.lesson14.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.lessonsvtb.lesson14.services.ProductDetailsService;

@Aspect
@Component
public class AspectLogger {
    private ProductDetailsService productDetailsService;

    @Autowired
    public void setProductDetailsService(ProductDetailsService productDetailsService) {
        this.productDetailsService = productDetailsService;
    }

    @Before("execution(public String ru.lessonsvtb.lesson14.controllers.ProductsController.showOneProduct(..))")
    public void logView(JoinPoint joinPoint){
        productDetailsService.logView((Long) joinPoint.getArgs()[1]);
    }

}
