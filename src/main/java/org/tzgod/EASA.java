package org.tzgod;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.util.Assert;
import org.tzgod.encryption.Encrypt;

import java.lang.reflect.Method;

public class EASA {
    private ProceedingJoinPoint joinPoint;
    private Method method;
    private Object[] args;
    private String PW;

    public EASA(ProceedingJoinPoint joinPoint, Method method, Object[] args) {
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
    public Object E(String password) {
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
        args[A] = Encrypt.utils((String) args[A]);
        //加密替换
        this.PW = (String) args[A];
        return Run(args);
    }

    //登录验证
    public Object S(String password) {
        Assert.isTrue(this.E(password).equals(PW), "密码错误");
        return "登录成功";
    }
    public Object a() {
        return null;
    }

    public Object A() {
        return null;
    }
}
