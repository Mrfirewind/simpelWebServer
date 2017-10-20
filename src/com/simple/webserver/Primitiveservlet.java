package com.simple.webserver;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

public class Primitiveservlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        PrintWriter printWriter = servletResponse.getWriter();
        //之试用于IE浏览器，其他浏览器无法输出，需要拼装完整的Response返回体才可以
        printWriter.println("Hello webserver");
        printWriter.print("1111");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
