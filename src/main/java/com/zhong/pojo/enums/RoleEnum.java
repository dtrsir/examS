package com.zhong.pojo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: zhong
 * @Description: 角色枚举类
 */
@Getter
@AllArgsConstructor
public enum RoleEnum {

    ADMIN(0, "管理员"),
    STUDENT(1, "学生");

    @EnumValue
    private Integer role;

    @JsonValue
    private String roleName;

}
