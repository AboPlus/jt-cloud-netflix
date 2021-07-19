package com.abo.jt.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Abo
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 7242246707878609296L;
    /** 用户id */
    private Integer id;
    /** 用户名 */
    private String username;
    /** 密码 */
    private String password;
}
