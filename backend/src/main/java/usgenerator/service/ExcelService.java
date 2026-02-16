package usgenerator.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import usgenerator.model.UserStory;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class ExcelService {

    // Store Excel + Domain together
    private static class ExcelData {
        String domain;
        byte[] file;

        ExcelData(String domain, byte[] file) {
            this.domain = domain;
            this.file = file;
        }
    }

    private final Map<Integer, ExcelData> excelStore = new HashMap<>();


    /**
     * Generate and store Excel for one combination
     */
    public void generateExcel(
            String domain,
            int combination,
            List<UserStory> stories
    ) {

        try (Workbook workbook = new XSSFWorkbook()) {

            Sheet sheet = workbook.createSheet("Combination " + combination);

            // ===== HEADER =====
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("User Story");
            header.createCell(1).setCellValue("Unique");
            header.createCell(2).setCellValue("Conflict Free");
            header.createCell(3).setCellValue("Uniform");
            header.createCell(4).setCellValue("Independent");
            header.createCell(5).setCellValue("Complete");

            // ===== DATA =====
            int rowIdx = 1;
            for (UserStory s : stories) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(s.getUserStory());
                row.createCell(1).setCellValue(s.isUnique());
                row.createCell(2).setCellValue(s.isConflictFree());
                row.createCell(3).setCellValue(s.isUniform());
                row.createCell(4).setCellValue(s.isIndependent());
                row.createCell(5).setCellValue(s.isComplete());
            }

            // Auto-size columns
            for (int i = 0; i < 6; i++) {
                sheet.autoSizeColumn(i);
            }

            // Save to memory
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);

            excelStore.put(combination, new ExcelData(domain, out.toByteArray()));

        } catch (Exception e) {
            throw new RuntimeException("Excel generation failed", e);
        }
    }


    /**
     * Download single combination Excel
     */
    public ResponseEntity<byte[]> downloadSingleExcel(int combination) {

        ExcelData data = excelStore.get(combination);

        if (data == null) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        }

        String safeDomain = data.domain.replaceAll("\\s+", "_");

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename="
                                + safeDomain
                                + "_Combination_"
                                + combination
                                + ".xlsx"
                )
                .contentType(
                        MediaType.parseMediaType(
                                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
                        )
                )
                .body(data.file);
    }


    /**
     * Download ALL combinations as ZIP
     */
    public ResponseEntity<byte[]> downloadAllExcel() {

        if (excelStore.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ZipOutputStream zos = new ZipOutputStream(baos)) {

            for (Map.Entry<Integer, ExcelData> entry : excelStore.entrySet()) {

                int combination = entry.getKey();
                ExcelData data = entry.getValue();

                String safeDomain = data.domain.replaceAll("\\s+", "_");

                ZipEntry zipEntry = new ZipEntry(
                        safeDomain + "_Combination_" + combination + ".xlsx"
                );

                zos.putNextEntry(zipEntry);
                zos.write(data.file);
                zos.closeEntry();
            }

            zos.finish();

            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=All_Combinations.zip"
                    )
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(baos.toByteArray());

        } catch (Exception e) {
            throw new RuntimeException("ZIP generation failed", e);
        }
    }
}
