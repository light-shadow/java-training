package com.general.demo.mvc.action;

import com.general.demo.service.IDemoService;
import com.general.mvcframework.annotation.SwordAutowired;
import com.general.mvcframework.annotation.SwordController;
import com.general.mvcframework.annotation.SwordRequestMapping;
import com.general.mvcframework.annotation.SwordRequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by general on 2019/6/23.
 */
@SwordController
@SwordRequestMapping("/demo")
public class DemoAction {

    @SwordAutowired private IDemoService demoService;

    @SwordRequestMapping("/query")
    public void query(HttpServletRequest req, HttpServletResponse resp,
                      @SwordRequestParam("name") String name){
        String result = demoService.getName(name);
//		String result = "My name is " + name;
        try {
            resp.getWriter().write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SwordRequestMapping("/add")
    public void add(HttpServletRequest req, HttpServletResponse resp,
                    @SwordRequestParam("a") Integer a, @SwordRequestParam("b") Integer b){
        try {
            resp.getWriter().write(a + "+" + b + "=" + (a + b));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SwordRequestMapping("/remove")
    public void remove(HttpServletRequest req,HttpServletResponse resp,
                       @SwordRequestParam("id") Integer id){
    }
}
