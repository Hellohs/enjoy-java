package com.dtian.java.design_pattern.structure.proxy.dynamic_proxy.cglib.interfae.impl;

import com.dtian.java.design_pattern.structure.proxy.dynamic_proxy.cglib.interfae.InterfaceService;

public class InterfaceServiceImpl implements InterfaceService{

    @Override
    public String queryServiceByName(String name) {
        System.out.println("query service by name[interface]");
        return "Q[interface]" + name;
    }
}
