package com.aaa.javabase.h2.Model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author liuzhen.tian
 * @version 1.0 UserBill.java  2022/10/21 19:49
 */

@Data
@TableName(value = "user_bill")
@AllArgsConstructor
@NoArgsConstructor
public class UserBill implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String createDate;

    private int money;

    private int userId;

    private User user;

    public UserBill(Long id, int money) {
        this.id = id;
        this.money = money;
    }

    @Override
    public String toString() {
        return "UserBill{" +
                "id=" + id +
                ", createDate='" + createDate + '\'' +
                ", money=" + money +
                ", userId=" + userId +
                '}';
    }
    // /**
    //  * writeReplace: 在序列化时，会先调用此方法，再调用writeObject方法。此方法可将任意对象代替目标序列化对象
    //  *
    //  * @return
    //  * @throws ObjectStreamException
    //  */
    // private Object writeReplace()  {
    //     return new UserBill(1L,100);
    // }
    //
    // /**
    //  * readResolve：反序列化时替换反序列化出的对象，反序列化出来的对象被立即丢弃。此方法在readeObject后调用。
    //  */
    // private Object readResolve() {
    //     return new UserBill(2L,200);
    // }

}
