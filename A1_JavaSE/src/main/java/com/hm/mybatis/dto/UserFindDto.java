package com.hm.mybatis.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserFindDto {
    private Long userId;
    private String userName;
}