package com.table.TableManager.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {
    public static String password(String epassword)
    {
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        return encoder.encode(epassword);
    }
    public static void main(String[]  args)
    {
        String epassword="123";
        String password=password(epassword);
        System.out.println("Encrypted password:"+password);
    }
}
