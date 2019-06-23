package com.general.servlet.v1;

import com.general.mvcframework.annotation.SwordAutowired;
import com.general.mvcframework.annotation.SwordController;
import com.general.mvcframework.annotation.SwordRequestMapping;
import com.general.mvcframework.annotation.SwordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

/**
 * Created by general on 2019/6/23.
 */
public class SwordDispatchServlet extends HttpServlet {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());


    // 放置初识时候的参数
    private Properties contextConfig = new Properties();

    // 保存所有扫描到的类名
    private List<String> classNameList = new ArrayList<String>();

    // ioc容器
    private Map<String, Object> iocContainer = new HashMap<String, Object>();

    // handlerMapping 容器 url-> method 对应关系
    private Map<String, Method> handlerMapping = new HashMap<String, Method>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            doDispatch(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("500 Exception Detail:" +Arrays.toString(e.getStackTrace()));
        }
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws IOException, InvocationTargetException, IllegalAccessException {
        // 绝对路径
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        // 处理成相对路径
        url = url.replaceAll(contextPath, "").replaceAll("/+", "/");
        if(!handlerMapping.containsKey(url)){
            resp.getWriter().write("404 Not Found!");
            return;
        }
        Method method = this.handlerMapping.get(url);
        // 通过反射 拿到method的class 拿到class的名称 之后拿到class的实例
        String beanName = toLowerFirstCase(method.getDeclaringClass().getSimpleName());
        // 在这里 将参数写死
        Map<String, String[]> params = req.getParameterMap();
        method.invoke(iocContainer.get(beanName), req, resp, params.get("name")[0]);
        logger.info(":)--->dispatch url completely!!!");

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        // 1、读取配置文件
        doLoadConfig(config.getInitParameter("contextConfigLocation"));

        // 2、扫描相关的类
        doScanner(contextConfig.getProperty("scanPackage"));

        // 3、扫描初始化的类
        initInstance();

        // 4、 装配
        doAutowired();

        // 5、初始化url和method的对照关系
        initHandlerMapping();
        logger.info(":)--->Sword framework init completely!");
    }


    private void initHandlerMapping() {
        if(iocContainer.isEmpty()){return;}
        for (Map.Entry<String, Object> entry: iocContainer.entrySet()) {
            Class<?> clazz = entry.getValue().getClass();
            // 如果class是被controller注解的 那么需要解析处理
            if(!clazz.isAnnotationPresent(SwordController.class)){continue;}
            String baseUrl = "";
            // 类上的总的url
            if(clazz.isAnnotationPresent(SwordRequestMapping.class)){
                SwordRequestMapping mapping = clazz.getAnnotation(SwordRequestMapping.class);
                baseUrl = mapping.value();
            }
            // 默认获取所有的public方法
            clazz.getMethods();
            // 循环每个方法 得到每个方法对应的url
            for (Method method: clazz.getMethods()) {
                if(!method.isAnnotationPresent(SwordRequestMapping.class)){continue;}
                SwordRequestMapping requestMapping = method.getAnnotation(SwordRequestMapping.class);
                // 优化
                // /demo/query
                String url = ("/" + baseUrl + "/" + requestMapping.value())
                        .replaceAll("/+", "/");
                handlerMapping.put(url, method);
                logger.info(":)---> Mapped: " + url  + " --> " + method);
            }
        }
    }

    /**
     * 对于ioc容器中的类进行装配
     */
    private void doAutowired() {
        if(iocContainer.isEmpty()){return;}
        for (Map.Entry<String, Object> entry: iocContainer.entrySet()) {
            // 将所有的属性全部都拿到
            // 一般的OOP 只能拿到public
            Field[] fields = entry.getValue().getClass().getDeclaredFields();
            for (Field f :fields) {
                if(!f.isAnnotationPresent(SwordAutowired.class)){
                    continue;
                }
                SwordAutowired autowired = f.getAnnotation(SwordAutowired.class);
                // 如果用户没有自定义beanName  默认就根据类型注入

                String beanName = autowired.value().trim();
                if("".equals(beanName)){
                    beanName = f.getType().getName();
                }
                f.setAccessible(true);
                try {
                    // 反射字段动态给字段赋值
                    f.set(entry.getValue(), iocContainer.get(beanName));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        logger.info(":)--->doAutowired finish!!!");
    }

    /*
    遍历扫描出来的类，然后实例化放入到ioc容器中
     */
    private void initInstance() {
        if(classNameList.isEmpty()){return;}
        // 遍历扫描到的类的list
        for(String className: classNameList){
            try {
                Class<?> clazz = Class.forName(className);
                // 什么样的类才需要初始化
                // 加了注解的类  接口的话实例化实现类
                if(clazz.isAnnotationPresent(SwordController.class) ) {
                    // 实例化 放入到ioc容器中
                    String beanName = toLowerFirstCase(clazz.getSimpleName());
                    // spring默认bean 首字母小写
                    Object instance = clazz.newInstance();
                    iocContainer.put(beanName, instance);
                }
                else if(clazz.isAnnotationPresent(SwordService.class)){
                    // 自定义的beanName 如果自定的beanName取自定义的beanName
                    // 没有的话 取默认的beanName 首字母小写的类名
                    SwordService swordService = clazz.getAnnotation(SwordService.class);
                    String beanName = swordService.value();
                    // 默认类名首字母小写
                    if("".equals(beanName.trim())){
                        beanName = toLowerFirstCase(clazz.getSimpleName());
                    }
                    Object instance = clazz.newInstance();
                    iocContainer.put(beanName, instance);
                    // 根据类型自动赋值
                    for (Class<?> i: clazz.getInterfaces()) {
                        if(iocContainer.containsKey(i.getName())){
                            throw new Exception("The '" + i.getName() + "' bean is existed!");
                        }
                        iocContainer.put(i.getName(), instance);
                    }

                }
                else {continue;}
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        logger.info(":)--->initInstance finish!!!");
    }

    /**
     * 大小写字母的ascii相差32 大写字母的ascii比小写字母的ascii少32
     * @param simpleName
     * @return
     */
    private String toLowerFirstCase(String simpleName) {
        char[] arrs = simpleName.toCharArray();
        arrs[0]+= 32;
        return String.valueOf(arrs);
    }


    /*
     扫描出相关的类
     */
    private void doScanner(String scanPackage) {
        // 包路径存储的 scanPackage=com.general.demo 需要转换为文件路径 把.替换为/
        URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\.", "\\/"));
        File classPath =  new File(url.getFile());
        for (File file: classPath.listFiles()) {
            // 过滤处理 是否是文件夹 文件夹递归
            if(file.isDirectory()) {doScanner(scanPackage + "." + file.getName());}
            else {
                // 不是class文件结尾的直接跳过
                if(!file.getName().endsWith(".class")){continue;}
                String className = (scanPackage + "." + file.getName().replace(".class", ""));
                classNameList.add(className);
            }
        }
        logger.info(":)--->initInstance finish!!!");
    }

    /**
     * 加载配置文件
     * @param config
     */
    private void doLoadConfig(String config) {
        // 直接从类路径下找到spring主配置文件所在的路径
        // 将其读取出来 放置到properties中
        // 相当于 scanPackage=com.general.demo 从文件中保存到了内存中
        InputStream fis = this.getClass().getClassLoader().getResourceAsStream(config);
        try {
            contextConfig.load(fis);
            logger.info(":)--->initInstance finish!!!");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(null != fis){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
