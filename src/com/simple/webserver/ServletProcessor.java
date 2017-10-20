package com.simple.webserver;
import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

public class ServletProcessor {
    public void process(Request request,Response response){
        String uri = request.getUri();
        String servletName = uri.substring(uri.lastIndexOf("/")+1);
        URLClassLoader loader = null;
        try{
            URL[] urls = new URL[1];
            URLStreamHandler streamHandler = null;
            System.out.println(System.getProperty("user.dir")+File.separator);
            File classPath = new File(System.getProperty("user.dir")+File.separator );
            String repository = (new URL("file",null,classPath.getCanonicalPath()+ File.separatorChar)).toString();
            urls[0] =new URL(null,repository,streamHandler);
            loader = new URLClassLoader(urls);
        }catch (Exception e){
             e.printStackTrace();
        }
        Class myclass = null;
        try {
            myclass = loader.loadClass("com.simple.webserver."+servletName);
        }catch (Exception e){
            e.printStackTrace();
        }
        Servlet servlet = null;
        try{
            servlet =(Servlet) myclass.newInstance();
            servlet.service((ServletRequest) request,(ServletResponse) response);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
