package com.laf.screencast.controllers;


import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.filters.ExpiresFilter;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.context.Context;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;
import java.util.Objects;

@Controller
public class MainScreenController {

    private Robot robot;
    private File file;
    private String fileName;
    private int switcher = 0;
    public MainScreenController() throws AWTException, IOException {
        System.setProperty("java.awt.headless", "false");
        robot = new Robot();
        BufferedImage image = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
//        fileName = "screenshot" + switcher + ".png";
//        file = new File("src/main/resources/static/Screen/" + fileName);
//        file = new File("src/main/resources/static/Screen/screenshot.png");
//        ImageIO.write(image, "png",file );
    }

    @GetMapping("/mainscreen")
    public String mainScreen(ModelMap model) throws IOException, NullPointerException {
//        model.addAttribute("fileName", fileName);


//        switcher++;
//        fileName = "screenshot" + switcher + ".png";
        BufferedImage image = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
//        file = new File("src/main/resources/static/Screen/" +fileName);
//        ImageIO.write(image, "png",file);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        InputStream is = new ByteArrayInputStream(baos.toByteArray());

        String pic ="data:image/png;base64," + Base64.getEncoder().encodeToString(baos.toByteArray());
        model.addAttribute("picture",pic);
//        Context context =  new Context();
//        context.setVariable("picture", pic);
//        model.addAttribute("pic",);
        //        file = new File("src/main/resources/static/Screen/screenshot.png");
//        file.delete();


        return "mainscreen";
    }
}
