package com.example.bhw.Bean;

import com.example.bhw.Dao.UserDao;
import com.example.bhw.Entity.User;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.regex.Pattern;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
@Stateless
public class UserVerificationBean {
    public final static String SUCCESS = "success!";

    @Inject
    private UserDao userDao;
    /**
     * 对密码加密存储
     * @param password:明文密码
     * @return 加密后的密码
     */
    private String encryptPassword(String password) {
        try {
            // 生成随机盐值
            SecureRandom sr = new SecureRandom();
            byte[] salt = new byte[16];
            sr.nextBytes(salt);

            // 将盐值和密码结合
            String saltedPassword = Base64.getEncoder().encodeToString(salt) + password;

            // 创建SHA-256 MessageDigest实例
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // 执行哈希计算
            byte[] hashedPassword = md.digest(saltedPassword.getBytes());

            // 将盐值和哈希后的密码一起存储，以便验证时使用
            return Base64.getEncoder().encodeToString(salt) + ":" + Base64.getEncoder().encodeToString(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error encrypting password", e);
        }
    }


    /**
     * 验证密码格式是否正确
     * @param password
     * @return
     */
    public boolean checkPasswordFormat(String password) {
        String pattern = "[0-9]{5,8}";
        boolean isMatch = Pattern.matches(pattern, password);
        return isMatch;
    }

    /**
     * 验证密码是否正确
     * @param password 用户输入的密码
     * @param encryptedPassword 存储的加密密码
     * @return
     */
    private boolean verifyPassword(String password, String encryptedPassword) {
        try {
            // 分割存储的加密密码和盐值
            String[] parts = encryptedPassword.split(":");
            String storedSalt = parts[0];
            String storedHash = parts[1];

            // 将存储的盐值与输入的密码结合
            String saltedPassword = storedSalt + password;

            // 创建SHA-256 MessageDigest实例
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // 执行哈希计算
            byte[] hashedPassword = md.digest(saltedPassword.getBytes());

            // 将计算出的哈希值与存储的哈希值进行比较
            return Base64.getEncoder().encodeToString(hashedPassword).equals(storedHash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error verifying password", e);
        }
    }

    /**
     * 验证密码是否正确
     * @param username
     * @param password
     * @return true:密码正确 false:密码错误
     */
    public User verifyIdentidy(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            return null;
        }
        if (!checkPasswordFormat(password)) {
            return null;
        }
        User user = userDao.getUserByName(username);
        if (user == null) {
            return null;
        }
        if (verifyPassword(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    /**
     * 注册用户
     * @param user 用户信息
     * @param rePassword 重复密码
     * @return 通知信息
     */
    public String RegisterUser(User user,String rePassword) {
        String info = SUCCESS;
        if(user == null) {
            info = "Sorry! User is null!";
            return info;
        }
        if(user.getUsername() == null || user.getUsername().isEmpty() || user.getPassword() == null || user.getPassword().isEmpty()) {
            info = "Sorry! Username or Password is null!";
            return info;
        }
        if(!checkPasswordFormat(user.getPassword())) {
            info = "Sorry! Password not inValid!";
            return info;
        }
        if(userDao.isUserExist(user.getUsername())) {
            info = "Sorry! Username already exist!";
            return info;
        }
        if(!user.getPassword().equals(rePassword)) {
            info = "Sorry! rePassword not match!";
            return info;
        }
        user.setPassword(encryptPassword(user.getPassword()));
        userDao.insertUser(user);

        return info;
    }
}
