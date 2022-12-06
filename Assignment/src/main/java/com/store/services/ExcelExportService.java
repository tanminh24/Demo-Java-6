package com.store.services;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.entities.Category;
import com.store.entities.Product;
import com.store.reponsitories.CategoryRepository;
import com.store.reponsitories.ProductRepository;

@Service
public class ExcelExportService {

    private XSSFWorkbook workbook; // lớp đại diện cho định dạng tệp excel
    private XSSFSheet sheetP; // sheet được gọi bởi XSSFWorkbook
    private XSSFSheet sheetC;

    @Autowired
    ProductRepository productRepo;

    @Autowired
    CategoryRepository categoryRepo;

    @Autowired
    HttpServletResponse response;

    public ExcelExportService() {
        this.workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheetC = workbook.createSheet("Categories");
        sheetP = workbook.createSheet("Products");

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        writeCategoryHeaderLine(style);
        writeProductHeaderLine(style);
    }

    private void writeCategoryHeaderLine(CellStyle style) {
        Row rowC = sheetC.createRow(0);
        createCell(rowC, 0, "ID", style);
        createCell(rowC, 1, "Tên danh mục", style);
    }

    private void writeProductHeaderLine(CellStyle style) {
        Row rowP = sheetP.createRow(0);
        createCell(rowP, 0, "ID", style);
        createCell(rowP, 1, "Danh mục", style);
        createCell(rowP, 2, "Tên sản phẩm", style);
        createCell(rowP, 3, "Ảnh sản phẩm", style);
        createCell(rowP, 4, "Giá", style);
        createCell(rowP, 5, "Số lượng", style);
        createCell(rowP, 6, "Ngày tạo", style);
        createCell(rowP, 7, "Trạng thái", style);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheetP.autoSizeColumn(columnCount); // chỉnh chiều rộng ô vừa nội dung
        sheetC.autoSizeColumn(columnCount);

        Cell cell = row.createCell(columnCount);
        // set kiểu giá trị cho ô
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof Date) {
            cell.setCellValue((Date) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        writeCategoryDataLine(style);
        writeProductDataLine(style);

    }

    public void writeProductDataLine(CellStyle style) {
        int rowCountP = 1;
        for (Product p : productRepo.findAll()) {
            Row rowP = sheetP.createRow(rowCountP++);
            int columnCount = 0;

            createCell(rowP, columnCount++, p.getId(), style);
            createCell(rowP, columnCount++, p.getCategory().getName(), style);
            createCell(rowP, columnCount++, p.getName(), style);
            createCell(rowP, columnCount++, p.getImage(), style);
            createCell(rowP, columnCount++, p.getPrice(), style);
            createCell(rowP, columnCount++, p.getQuantity(), style);
            createCell(rowP, columnCount++, String.valueOf(p.getCreatedDate()), style);
            createCell(rowP, columnCount++, (p.getAvailable() == 0) ? "Đang bán" : "Ngưng bán", style);
        }
    }

    public void writeCategoryDataLine(CellStyle style) {
        int rowCountC = 1;
        for (Category c : categoryRepo.findAll()) {
            Row rowC = sheetC.createRow(rowCountC++);
            int columnCount = 0;

            createCell(rowC, columnCount++, c.getId(), style);
            createCell(rowC, columnCount++, c.getName(), style);
        }
    }

    public void export() throws IOException {
        // Thiết lập thông tin file
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerValue = "attachment; filename = Apple-Manager_" + currentDateTime + ".xlsx";
        response.setHeader("Content-disposition", headerValue);

        writeHeaderLine();
        writeDataLines();

        // Ghi file ra response outputstream
        // ServletOutputStream là một lớp byte-stream
        // có thể được sử dụng để ghi các giá trị nguyên thủy cũng như các thông tin
        // character-based.
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

}
