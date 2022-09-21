package com.hm.poi;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.*;
import java.util.Calendar;
import java.util.Date;

public class p01 {


    @Test
    public void create_a_new_workbook() {
        Workbook wb = new HSSFWorkbook();
        try (OutputStream fileOut = new FileOutputStream("workbook.xls")) {
            wb.write(fileOut);
        } catch (Exception ex) {
            return;
        }
        Workbook wb2 = new XSSFWorkbook();
        try (OutputStream fileOut = new FileOutputStream("workbook.xlsx")) {
            wb2.write(fileOut);
        } catch (Exception ex) {
            return;
        }
    }

    @Test
    public void create_a_sheet() {
        Workbook wb = new XSSFWorkbook();
        Sheet sheet1 = wb.createSheet("new sheet");
        Sheet sheet2 = wb.createSheet("second sheet");

        try (OutputStream fileOut = new FileOutputStream("workbook2.xlsx")) {
            wb.write(fileOut);
        } catch (Exception ex) {
            return;
        }


    }

    @Test
    public void create_cells() {
        Workbook wb = new XSSFWorkbook();
        CreationHelper createHelper = wb.getCreationHelper();
        Sheet sheet = wb.createSheet("new sheet");

        // Create a row and put some cells in it. Rows are 0 based.
        Row row = sheet.createRow(0);

        // Create a cell and put a value in it.
        Cell cell = row.createCell(0);
        cell.setCellValue(1);


        // Or do it on one line.
        row.createCell(1).setCellValue(1.2);
        row.createCell(2).setCellValue(createHelper.createRichTextString("This is a string"));
        row.createCell(3).setCellValue(true);

        // Write the output to a file
        try (OutputStream fileOut = new FileOutputStream("workbook3.xlsx")) {
            wb.write(fileOut);
        } catch (Exception ex) {
            return;
        }
    }


    @Test
    public void create_date_cells() {
        Workbook wb = new XSSFWorkbook();
        CreationHelper createHelper = wb.getCreationHelper();
        Sheet sheet = wb.createSheet("new sheet");

// Create a row and put some cells in it. Rows are 0 based.
        Row row = sheet.createRow(0);
// Create a cell and put a date value in it.  The first cell is not styled
// as a date.
        Cell cell = row.createCell(0);
        cell.setCellValue(new Date());
// we style the second cell as a date (and time).  It is important to
// create a new cell style from the workbook otherwise you can end up
// modifying the built in style and effecting not only this cell but other cells.

        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
        cell = row.createCell(1);
        cell.setCellValue(new Date());
        cell.setCellStyle(cellStyle);

//you can also set date as java.util.Calendar
        cell = row.createCell(2);
        cell.setCellValue(Calendar.getInstance());
        cell.setCellStyle(cellStyle);
// Write the output to a file
        // Write the output to a file
        try (OutputStream fileOut = new FileOutputStream("workbook4.xlsx")) {
            wb.write(fileOut);
        } catch (Exception ex) {
            return;
        }

    }


    @Test
    public void Working_with_different_types_of_cells() {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("new sheet");
        Row row = sheet.createRow(2);
        row.createCell(0).setCellValue(1.1);
        row.createCell(1).setCellValue(new Date());
        row.createCell(2).setCellValue(Calendar.getInstance());
        row.createCell(3).setCellValue("a string");
        row.createCell(4).setCellValue(true);
        row.createCell(5).setCellType(CellType.ERROR);

// Write the output to a file
        try (OutputStream fileOut = new FileOutputStream("workbook5.xlsx")) {
            wb.write(fileOut);
        } catch (Exception ex) {
            return;
        }
    }


    @Test
    public void load_from_file_or_inputStream() throws Exception {

        // Use a file
        Workbook wb = WorkbookFactory.create(new File("workbook5.xlsx"));

        try (OutputStream fileOut = new FileOutputStream("workbook6.xlsx")) {
            wb.write(fileOut);
        } catch (Exception ex) {
            return;
        }

        // // Use an InputStream, needs more memory
        wb = WorkbookFactory.create(new FileInputStream("workbook5.xlsx"));

        try (OutputStream fileOut = new FileOutputStream("workbook7.xlsx")) {
            wb.write(fileOut);
        } catch (Exception ex) {
            return;
        }
    }


    // 设置单元格的高度，填充单元格的内容的位置
    @Test
    public void Demonstrates_various_alignment_options() {
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet();
        Row row = sheet.createRow(2);
        row.setHeightInPoints(30);
        createCell(wb, row, 0, HorizontalAlignment.CENTER, VerticalAlignment.BOTTOM);
        createCell(wb, row, 1, HorizontalAlignment.CENTER_SELECTION, VerticalAlignment.BOTTOM);
        createCell(wb, row, 2, HorizontalAlignment.FILL, VerticalAlignment.CENTER);
        createCell(wb, row, 3, HorizontalAlignment.GENERAL, VerticalAlignment.CENTER);
        createCell(wb, row, 4, HorizontalAlignment.JUSTIFY, VerticalAlignment.JUSTIFY);
        createCell(wb, row, 5, HorizontalAlignment.LEFT, VerticalAlignment.TOP);
        createCell(wb, row, 6, HorizontalAlignment.RIGHT, VerticalAlignment.TOP);

        // Write the output to a file
        try (OutputStream fileOut = new FileOutputStream("workbook8.xlsx")) {
            wb.write(fileOut);
        } catch (Exception ex) {
            return;
        } finally {
            try {
                wb.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Creates a cell and aligns it a certain way.
     *
     * @param wb     the workbook
     * @param row    the row to create the cell in
     * @param column the column number to create the cell in
     * @param halign the horizontal alignment for the cell.
     * @param valign the vertical alignment for the cell.
     */
    private static void createCell(Workbook wb, Row row, int column, HorizontalAlignment halign, VerticalAlignment valign) {
        Cell cell = row.createCell(column);
        cell.setCellValue("Align It");
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(halign);
        cellStyle.setVerticalAlignment(valign);
        cell.setCellStyle(cellStyle);
    }

    // 设置单元格的边框，颜色
    @Test
    public void Working_with_borders() throws Exception {
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("new sheet");
// Create a row and put some cells in it. Rows are 0 based.
        Row row = sheet.createRow(1);
// Create a cell and put a value in it.
        Cell cell = row.createCell(1);
        cell.setCellValue(4);
// Style the cell with borders all around.
        CellStyle style = wb.createCellStyle();
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.GREEN.getIndex());
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLUE.getIndex());
        style.setBorderTop(BorderStyle.MEDIUM_DASHED);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        cell.setCellStyle(style);
// Write the output to a file
        try (OutputStream fileOut = new FileOutputStream("workbook9.xls")) {
            wb.write(fileOut);
        }
        wb.close();
    }


    @Test
    public void Fills_and_colors() throws Exception {
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("new sheet");
// Create a row and put some cells in it. Rows are 0 based.
        Row row = sheet.createRow(1);
// Aqua background
        CellStyle style = wb.createCellStyle();
        style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
        style.setFillPattern(FillPatternType.BIG_SPOTS);
        Cell cell = row.createCell(1);
        cell.setCellValue("X");
        cell.setCellStyle(style);
// Orange "foreground", foreground being the fill foreground not the font color.
        style = wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell = row.createCell(2);
        cell.setCellValue("X");
        cell.setCellStyle(style);
// Write the output to a file

// Write the output to a file
        try (OutputStream fileOut = new FileOutputStream("workbook10.xls")) {
            wb.write(fileOut);
        }
        wb.close();
    }

    @Test
    public void Merging_cells() throws Exception {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("new sheet");
        Row row = sheet.createRow(1);
        Cell cell = row.createCell(1);
        cell.setCellValue("This is a test of merging");
        sheet.addMergedRegion(new CellRangeAddress(
                1, //first row (0-based)
                1, //last row  (0-based)
                1, //first column (0-based)
                2  //last column  (0-based)
        ));

// Write the output to a file
        try (OutputStream fileOut = new FileOutputStream("workbook11.xls")) {
            wb.write(fileOut);
        }
        wb.close();
    }

}
