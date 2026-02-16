package usgenerator.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import usgenerator.model.UserStory;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class ExcelGenerator {

    private ExcelGenerator() {
        // Utility class
    }

    public static byte[] generateExcel(
            String sheetName,
            List<UserStory> stories
    ) {

        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            Sheet sheet = workbook.createSheet(sheetName);

            // ================= HEADER STYLE =================
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);

            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFont(headerFont);

            // ================= HEADER =================
            Row header = sheet.createRow(0);
            String[] columns = {
                    "User Story",
                    "Unique",
                    "Conflict Free",
                    "Uniform",
                    "Independent",
                    "Complete"
            };

            for (int i = 0; i < columns.length; i++) {
                Cell cell = header.createCell(i);
                cell.setCellValue(columns[i]);
                cell.setCellStyle(headerStyle);
            }

            // ================= DATA =================
            int rowIndex = 1;
            for (UserStory story : stories) {

                Row row = sheet.createRow(rowIndex++);

                row.createCell(0).setCellValue(story.getUserStory());
                row.createCell(1).setCellValue(story.isUnique() ? "Yes" : "No");
                row.createCell(2).setCellValue(story.isConflictFree() ? "Yes" : "No");
                row.createCell(3).setCellValue(story.isUniform() ? "Yes" : "No");
                row.createCell(4).setCellValue(story.isIndependent() ? "Yes" : "No");
                row.createCell(5).setCellValue(story.isComplete() ? "Yes" : "No");
            }

            // ================= AUTO SIZE =================
            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(out);
            return out.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Failed to generate Excel file", e);
        }
    }
}
