package com.mp.utils;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public class ValidateCode {
    private int width = 160;
    private int height = 40;
    private int codeCount = 5;
    private int lineCount = 150;
    private String code = null;
    private BufferedImage buffImg = null;
    private final char[] codeSequence = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public ValidateCode() {
        this.createCode();
    }

    public ValidateCode(int width, int height) {
        this.width = width;
        this.height = height;
        this.createCode();
    }

    public ValidateCode(int width, int height, int codeCount, int lineCount) {
        this.width = width;
        this.height = height;
        this.codeCount = codeCount;
        this.lineCount = lineCount;
        this.createCode();
    }

    public void createCode() {
        //int x = 0;
        //int fontHeight = 0;
        //int codeY = 0;
        int red = 0;
        int green = 0;
        int blue = 0;
        int x = this.width / (this.codeCount + 2);
        int fontHeight = this.height - 2;
        int codeY = this.height - 4;
        this.buffImg = new BufferedImage(this.width, this.height, 1);
        Graphics2D g = this.buffImg.createGraphics();
        Random random = new Random();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.width, this.height);
        ImgFontByte imgFont = new ImgFontByte();
        Font font = imgFont.getFont(fontHeight);
        g.setFont(font);

        //int i;
        //int red;
        //int green;
        //int blue;
        for(int i = 0; i < this.lineCount; ++i) {
            i = random.nextInt(this.width);
            int ys = random.nextInt(this.height);
            int xe = i + random.nextInt(this.width / 8);
            int ye = ys + random.nextInt(this.height / 8);
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);
            g.setColor(new Color(red, green, blue));
            g.drawLine(i, ys, xe, ye);
        }

        StringBuffer randomCode = new StringBuffer();

        for(int i = 0; i < this.codeCount; ++i) {
            String strRand = String.valueOf(this.codeSequence[random.nextInt(this.codeSequence.length)]);
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);
            g.setColor(new Color(red, green, blue));
            g.drawString(strRand, (i + 1) * x, codeY);
            randomCode.append(strRand);
        }

        this.code = randomCode.toString();
    }

    public void write(String path) throws IOException {
        OutputStream sos = new FileOutputStream(path);
        this.write(sos);
    }

    public void write(OutputStream sos) throws IOException {
        ImageIO.write(this.buffImg, "png", sos);
        sos.close();
    }

    public BufferedImage getBuffImg() {
        return this.buffImg;
    }

    public String getCode() {
        return this.code;
    }
}
