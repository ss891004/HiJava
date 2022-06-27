package com.hm.utils;

import java.io.IOException;
import java.util.Date;

public class ValidateCodeTest {
    public ValidateCodeTest() {
    }

    public static void main(String[] args) {
        ValidateCode vCode = new ValidateCode(120, 40, 5, 100);

        try {
            String path = "D:/t/" + (new Date()).getTime() + ".png";
            System.out.println(vCode.getCode() + " >" + path);
            vCode.write(path);
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }
}
