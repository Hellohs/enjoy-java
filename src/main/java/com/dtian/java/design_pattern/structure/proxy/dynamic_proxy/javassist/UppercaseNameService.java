package com.dtian.java.design_pattern.structure.proxy.dynamic_proxy.javassist;

import org.springframework.util.Assert;

public class UppercaseNameService implements NameService {

    @Override
    public String changeName(String name) {
        Assert.notNull(name);
        String[] ns = name.split("\\s+");
        String res = "";
        for (int i = 0; i < ns.length; i++) {
            String temp = ns[i].substring(0, 1).toUpperCase().concat(ns[i].substring(1, ns[i].length()).toLowerCase());
            res += temp + " ";
        }
        return res;
    }
}
