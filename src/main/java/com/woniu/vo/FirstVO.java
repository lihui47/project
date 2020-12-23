package com.woniu.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @ClassName FirstVO
 * @Description TODO
 * @Authro SheepSun
 * @Date 2020/12/22 19:31
 * @Version 1.0
 **/
@Component
@Data
public class FirstVO implements Serializable {
    private String name;
    private int id;
    private String secondName;
    private String danwei="个";
    private String level="二级";
}
