package com.aweika;

/**
 * @author: Michael
 * @date: 2020/3/31
 * @description:
 */
public class User {
    private String name;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }
}
