package ru.sysout.jwt.security;

public class AuthResponse {
    private String jwtToken;

    public AuthResponse() {
    }

    public AuthResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }


}
