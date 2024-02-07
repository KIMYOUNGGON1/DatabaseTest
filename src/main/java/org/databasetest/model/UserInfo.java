package org.databasetest.model;

import lombok.Data;

@Data
public class UserInfo {
    private Long id;
    private String username;
    private String password;
}
