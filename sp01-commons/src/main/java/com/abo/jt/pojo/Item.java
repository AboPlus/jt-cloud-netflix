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
public class Item implements Serializable {
    private static final long serialVersionUID = -6185982975149385645L;
    /** 商品id */
    private Integer id;
    /** 商品名称 */
    private String name;
    /** 商品数量 */
    private Integer number;
}
