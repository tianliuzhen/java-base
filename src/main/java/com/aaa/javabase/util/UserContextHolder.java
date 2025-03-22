package com.aaa.javabase.util;

import com.aaa.javabase.domain.dto.UserDTO;

/**
 * @author liuzhen.tian
 * @version 1.0 User.java  2025/3/22 16:16
 */
public class UserContextHolder {

    private static ThreadLocal<UserDTO> userLocal = new ThreadLocal<>();


    public static void setUserName(String nickName) {
        userLocal.set(new UserDTO(nickName));
    }

    public static String getUserName() {
        UserDTO userDTO = userLocal.get();
        if (userDTO == null) {
            return null;
        }
        return userDTO.getName();
    }

    public static UserDTO getUserDTO() {
        UserDTO userDTO = userLocal.get();
        if (userDTO == null) {
            return null;
        }
        return userDTO;
    }
    public static void setUserDTO(UserDTO userDTO) {
        userLocal.set(userDTO);
    }

    public static void clear(){
        userLocal.remove();
    }
}
