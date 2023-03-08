package com.example;


import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DemoController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(ServletInputStream sis = req.getInputStream()){
            byte[] readBytes = new byte[1024];
            int len =0;
            while((len = sis.read(readBytes))!=-1) {
                String msg = new String(readBytes, 0, len);
                System.out.println("msg = " + msg );
            }
        }
        System.out.println("read over");

        try (PrintWriter writer = resp.getWriter()) {
            writer.write("hello world");
            writer.flush();
        }catch (Exception e) {
            e.printStackTrace();
            e.fillInStackTrace();
        }
    }
}
