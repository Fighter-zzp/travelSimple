package com.zzp.travel.controller.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/CheckCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //服务器通知浏览器不要缓存
        resp.setHeader("pragma","no-cache");
        resp.setHeader("cache-control","no-cache");
        resp.setHeader("expires","0");
        int width = 100;
        int height = 40;
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = bi.getGraphics();
        graphics.setColor(Color.black);
        graphics.fillRect(0,0,width,height);
        String checkCode = getCode();
        req.getSession().setAttribute("checkCode",checkCode);
        graphics.setColor(Color.pink);
        graphics.setFont(new Font("黑体",Font.BOLD,24));
        //向图片上写入验证码
        graphics.drawString(checkCode,15,25);
        // 改变画笔颜色为绿色
        graphics.setColor(Color.magenta);
        // 绘制干扰线
        for (int i = 0;i < 10;i++){
            // 干扰线x轴的开始,y轴的开始,x轴的结束,y轴的结束
            graphics.drawLine(new Random().nextInt(width),
                    new Random().nextInt(height),new Random().nextInt(width),new Random().nextInt(height));
        }
        ImageIO.write(bi,"jpg",resp.getOutputStream());

    }

    private String getCode() {
        String baseCode = "0123456789ABCDEFabcdef";//十六进制输出验证码
        int size = baseCode.length();
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            char code = baseCode.charAt(random.nextInt(size));
            builder.append(code);//添加随机验证码
        }
        return builder.toString();
    }
}
