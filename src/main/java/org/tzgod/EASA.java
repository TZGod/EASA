package org.tzgod;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.util.Assert;
import org.tzgod.config.Encrypted;

import java.lang.reflect.Method;

public class EASA {
    private ProceedingJoinPoint joinPoint;
    private Method method;
    private Object[] args;
    private String PW;

    public EASA(ProceedingJoinPoint joinPoint,Method method,Object[] args) {
        this.joinPoint=joinPoint;
        this.method = method;
        this.args = args;
    }
    //继续运行
    public Object Run( Object[] args)  {
        Object returnValue = null;
        try {
            returnValue = joinPoint.proceed(args);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return returnValue;
    }


    //加密
    public Object E(String password,Encrypted encrypted) {
        int A = 0;

        if (!(password == null || password.equals(""))) {
            DefaultParameterNameDiscoverer discoverer = new DefaultParameterNameDiscoverer();
            String[] parameterNames = discoverer.getParameterNames(method);
            for (int i = 0; i < parameterNames.length; i++) {
                if (parameterNames[i].equals(password)) {
                    A = i;
                }
            }
        }
        args[A] = encrypted.Encrypted((String) args[A]);
        //加密替换
        this.PW = (String) args[A];
        return Run(args);
    }

    //登录验证
    public Object S(String password,Encrypted encrypted) {
        Assert.isTrue(this.E(password,encrypted).equals(PW), "密码错误");
        return "登录成功";
    }
}

