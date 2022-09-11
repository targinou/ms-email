package com.ms.email.dtos;

import io.jsonwebtoken.Claims;

public class JwtResponse {

    private Integer id;
    private String login;
    private String password;

    public static JwtResponse getUser (Claims jwtClaims){
        try {
            return new JwtResponse((Integer) jwtClaims.get("id"), (String) jwtClaims.get("login"), (String) jwtClaims.get("password"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public JwtResponse(Integer id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
