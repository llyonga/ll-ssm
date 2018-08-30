//package com.io.ssm.framework.servlet;
//
//import com.io.ssm.framework.utils.Constants;
//import com.io.ssm.framework.utils.RandomUtils;
//
//import java.awt.Color;
//import java.awt.Font;
//import java.awt.Graphics2D;
//import java.awt.RenderingHints;
//import java.awt.image.BufferedImage;
//import java.io.IOException;
//import java.util.Random;
//
//import javax.imageio.ImageIO;
//import javax.servlet.Servlet;
//import javax.servlet.ServletConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * @description:
// * @author: llyong
// * @date: 2018/8/30
// * @time: 10:40
// * @version: 1.0
// */
//public class CaptchaServlet extends HttpServlet implements Servlet {
//
//    private static final long serialVersionUID = 1L;
//
//    @Override
//    public void init(ServletConfig conf) throws ServletException {
//        super.init(conf);
//        ImageIO.setUseCache(false);
//    }
//
//    @Override
//    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setDateHeader("Expires", 0);
//        resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
//        resp.addHeader("Cache-Control", "post-check=0, pre-check=0");
//        resp.setHeader("Pragma", "no-cache");
//        resp.setContentType("image/jpeg");
//
//        // create the text for the image
//        String capText = RandomUtils.getRandomNumber(4);
//
//        req.getSession().setAttribute(Constants.SESSION_CAPTCHA_CODE, capText);
//
//        // create the image with the text
//        BufferedImage bi = createImage(66, 35, capText);
//
//        ServletOutputStream out = resp.getOutputStream();
//
//        // write the data out
//        ImageIO.write(bi, "jpg", out);
//    }
//
//
//
//    private static BufferedImage createImage(int width, int height, String text) {
//        //Font font = new Font("Serif", Font.BOLD, 10);
//        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//        Graphics2D g2 = (Graphics2D) bi.getGraphics();
//        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
//        g2.setColor(Color.WHITE);// 设置边框色
//        g2.fillRect(0, 0, width, height);
////		Color c = getRandColor(204, 205);
//        g2.setColor(new Color(255, 255, 255));// 设置背景色
//        g2.fillRect(0, 2, width,height-5);
//        g2.setColor(new Color(102, 102, 102));
//        int fontSize = height-19;
//        Font font = new Font("微软雅黑", Font.ITALIC, fontSize);
//        g2.setFont(font);
//        char[] chars = text.toCharArray();
//        for(int i = 0; i < 4; i++){
//            //设置倾斜度
//            g2.drawChars(chars, i, 1, ((width) / 5) * i + 7, height/2 + fontSize/2-2);
//        }
//
//        g2.dispose();
//        return bi;
//    }
//
//    private static Color getRandColor(int fc, int bc) {
//        Random random = new Random();
//        if (fc > 255) {
//            fc = 255;
//        }
//        if (bc > 255) {
//            bc = 255;
//        }
//        int r = fc + random.nextInt(bc - fc);
//        int g = fc + random.nextInt(bc - fc);
//        int b = fc + random.nextInt(bc - fc);
//        return new Color(r, g, b);
//    }
//}
