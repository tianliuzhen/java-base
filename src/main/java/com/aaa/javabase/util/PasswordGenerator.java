package com.aaa.javabase.util;

import java.util.Random;

/**
 * @author liuzhen.tian
 * @version 1.0 PasswordGenerator.java  2023/11/4 11:11
 */
public class PasswordGenerator {
    // 定义包含数字、字符和特殊符号的字符集
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_-+=<>?";

    public static String generatePassword(int length) {
        Random random = new Random();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            password.append(randomChar);
        }
        return password.toString();
    }

    public static void main(String[] args) {
        int passwordLength = 18; // 设置密码长度
        String generatedPassword = generatePassword(passwordLength);
        System.out.println(generatedPassword);
    }
}
