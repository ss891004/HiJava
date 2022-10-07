package com.mp.itext5;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class demo {

    public void Test1() throws FileNotFoundException, DocumentException {
        /**
         * 5步生成一个PDF
         * 创建文档实例 Document
         * 获取PdfWriter实例 （需要指定Document实例 和pdf 生成的磁盘路径）
         * 打开文档
         * 添加段落内容
         * 关闭操作文档实例 （操作完成后必须执行文档关闭操作）
         */
        FileOutputStream fos = new FileOutputStream("/Users/shuaishi/a.pdf");

        // 1-创建文本对象 Document
        //Document document = new Document(PageSize.A4, 500, 150, 50, 50);

        Document document = new Document(PageSize.A4);

        // 2-初始化 pdf输出对象 PdfWriter
        PdfWriter.getInstance(document, fos);

        // 3-打开 Document
        document.open();

        // 4-往 Document 添加内容
        document.add(new Paragraph("Hello！ PDF！！！"));

        // 5-关闭 Document
        document.close();

    }
}
