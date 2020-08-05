package org.tzgod.config;

public class Encrypted {
    public Class<?> T;
    public Encrypted(Class<?> T){
        this.T=T;
    }


    public String Encrypted(String password){
        ConFig conFig =null;
        String encrypt = null;
        try {
            conFig = (ConFig)T.newInstance();
            encrypt = conFig.Encrypt(password);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return encrypt;
    }
}
