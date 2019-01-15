package com.management.server.web.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Łukasz on 13.01.2019
 */
@Getter
@Setter
public class AuthRequestDto {
    private String username;
    private String password;
}
