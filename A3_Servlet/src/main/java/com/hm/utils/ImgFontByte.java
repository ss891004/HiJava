package com.hm.utils;


import java.awt.*;
import java.io.ByteArrayInputStream;

public class ImgFontByte {
    public ImgFontByte() {
    }

    public Font getFont(int fontHeight) {
        try {
            Font baseFont = Font.createFont(0, new ByteArrayInputStream(this.hex2byte(this.getFontByteStr())));
            return baseFont.deriveFont(0, (float)fontHeight);
        } catch (Exception var3) {
            return new Font("Arial", 0, fontHeight);
        }
    }

    private byte[] hex2byte(String str) {
        if (str == null) {
            return null;
        } else {
            str = str.trim();
            int len = str.length();
            if (len != 0 && len % 2 != 1) {
                byte[] b = new byte[len / 2];

                try {
                    for(int i = 0; i < str.length(); i += 2) {
                        //b[i / 2] = (byte)Integer.decode("0x" + str.substring(i, i + 2));
                    }

                    return b;
                } catch (Exception var5) {
                    return null;
                }
            } else {
                return null;
            }
        }
    }

    private String getFontByteStr() {
        return "xxx";
    }
}
