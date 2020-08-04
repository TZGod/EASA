package org.tzgod.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.tzgod.EASA;
import org.tzgod.annotation.Epw;
import org.tzgod.annotation.Sign;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Component
@Aspect
public class MyAspect {
    @Pointcut("@annotation(org.tzgod.annotation.Easa)")
    public  void Easa(){}



    @Around("Easa()")
    public Object EasaSome(ProceedingJoinPoint joinPoint) throws Throwable {
        Object O = null ;
        //获取方法签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取方法参数
        Object[] args = joinPoint.getArgs();
        //获取方法
        Method method = signature.getMethod();
        EASA encrypted = new EASA(joinPoint,method,args);
        //获取方法上所有注解
        Annotation[] annotations = method.getAnnotations();
        //判断EASa    E加密 A认证 S登录 a授权
        for (int i = 0; i < annotations.length; i++) {
            if (annotations[i].annotationType().equals(Epw.class)){
                //获取方法上注解参数
                String password = method.getAnnotation(Epw.class).password();
                O = encrypted.E(password);
                i=annotations.length;
            }else if (annotations[i].annotationType().equals(Sign.class)){
                //获取方法上注解参数
                String password = method.getAnnotation(Sign.class).password();
                O = encrypted.S(password);
                i=annotations.length;
            }
        }
        return O;
    }


}