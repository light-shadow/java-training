package com.general.demo.mvc.action;

import com.general.demo.service.IDemoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by general on 2019/6/23.
 */
public class OneAction {

    private IDemoService demoService;

    public void edit(HttpServletRequest req, HttpServletResponse resp,
                     String name){
        String result = demoService.getName(name);
        try {
            resp.getWriter().write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
