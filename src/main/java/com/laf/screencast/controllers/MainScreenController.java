package com.laf.screencast.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;

@Controller
public class MainScreenController {

    private Robot robot;
    public MainScreenController() throws AWTException, IOException {
        System.setProperty("java.awt.headless", "false");
        robot = new Robot();
        BufferedImage image = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
    }

    @GetMapping(value ="/mainscreen",produces = "multipart/x-mixed-replace;boundary=frame")
    public String mainScreen(HttpServletResponse response, OutputStream outputStream) throws IOException, NullPointerException, InterruptedException {
        response.setContentType("multipart/x-mixed-replace;boundary=frame");
        OutputStream out = response.getOutputStream();
        while(true){
        BufferedImage image = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
//        InputStream is = new ByteArrayInputStream(baos.toByteArray());
//        String pic ="data:image/png;base64," + Base64.getEncoder().encodeToString(baos.toByteArray());
            byte[] imageBytes = baos.toByteArray();
            out.write("--frame\r\nContent-Type: image/jpeg\r\n\r\n".getBytes());
            out.write(imageBytes);
            out.write("\r\n".getBytes());
            out.flush();
            Thread.sleep(200);
//        model.addAttribute("picture",pic);
//        return "mainscreen";
        }
    }
}
