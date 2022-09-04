package com.example.systemcruddesign;

public class Users {
    private String loginid;
    private String username;
    private String email;
    private String mobile;
    private String password;

    public Users(String loginid, String username, String email, String mobile, String password) {
        this.loginid = loginid;
        this.username = username;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
    }

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
