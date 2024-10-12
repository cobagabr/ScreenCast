package com.laf.screencast.controllers;

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

    @GetMapping("/mainscreen")
    public String mainScreen(ModelMap model) throws IOException, NullPointerException {
        BufferedImage image = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        InputStream is = new ByteArrayInputStream(baos.toByteArray());
        String pic ="data:image/png;base64," + Base64.getEncoder().encodeToString(baos.toByteArray());
        model.addAttribute("picture",pic);
        return "mainscreen";
    }
}
