package com.minions.admin.bean;

import java.util.HashSet;
import java.util.Set;

public class UserLoginInfo {
    private String username;
    private Set<String> routes = new HashSet<>();

    public UserLoginInfo() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<String> getRoutes() {
        return routes;
    }

    public void setRoutes(Set<String> routes) {
        this.routes = routes;
    }
}
