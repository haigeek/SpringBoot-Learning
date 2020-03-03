package com.haigeek.querydsl.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author zhaohj
 * @date 2020-03-03 14:04
 */
@Data
@AllArgsConstructor
public class UserPermissionDTO {
    private Integer userId;
    private String userName;
    private Integer permissionId;
    private String permission;
}
