package com.general.demo.service.impl;

import com.general.demo.service.IDemoService;
import com.general.mvcframework.annotation.SwordService;

/**
 * Created by general on 2019/6/23.
 */
@SwordService
public class DemoService implements IDemoService{

    @Override
    public String getName(String name) {
        return "My name is " + name;
    }
}
