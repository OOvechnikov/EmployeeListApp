package ru.ovechnikov.emplist.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import ru.ovechnikov.emplist.api.request.ExportRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelExporter {

    public void exportExc(HttpServletResponse response, ExportRequest request) {

        try (XSSFWorkbook workbook = new XSSFWorkbook();
             ServletOutputStream outputStream = response.getOutputStream()) {

            fillData(workbook, request);

            workbook.write(outputStream);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fillData(XSSFWorkbook workbook, ExportRequest request) {
        XSSFSheet sheet = workbook.createSheet("Employees");
        List<String> head = request.getHead();
        List<List<String>> body = request.getBody();

        Row row = sheet.createRow(0);
        Cell cell;
        for (int i = 0; i < head.size(); i++) {
            cell = row.createCell(i);
            cell.setCellValue(head.get(i));
        }
        for (int i = 0; i < body.size(); i++) {
            row = sheet.createRow(i + 1);
            List<String> rowContent = body.get(i);
            for (int j = 0; j < rowContent.size(); j++) {
                cell = row.createCell(j);
                cell.setCellValue(rowContent.get(j));
            }
        }
    }
}
