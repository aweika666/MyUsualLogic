package com.aweika.ConditionalOnXXXTest;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Documented;

/**
 * @author: Michael
 * @date: 2020/2/5
 * @description:
 */
//@Repository("PC")
public class Computer {
    private String name;

    public Computer() {
        System.out.println("PCPCPCPCPCPCPC");
    }

    public Computer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Computer setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .toString();
    }
}
