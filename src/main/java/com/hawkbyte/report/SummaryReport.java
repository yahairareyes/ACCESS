/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hawkbyte.report;

import com.hawkbyte.model.Initiative;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;


/**
 *
 * @author raizo
 */
public class SummaryReport extends AbstractExcelView{
    
      public void summaryReport(List<Initiative> initiatives) throws Exception{
         try {
            
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Summary Report");  
            
            HSSFRow header = sheet.createRow((short)0);
            header.createCell(0).setCellValue("Title");
            header.createCell(1).setCellValue("Description");
            header.createCell(2).setCellValue("Owner");
            header.createCell(3).setCellValue("Category");
            header.createCell(3).setCellValue("Creation Date");
            
            for(int i = 0;i<initiatives.size();i++){
                
                
                Initiative initiative = initiatives.get(i);
                HSSFRow row = sheet.createRow((short)i+1);
               
                row.createCell(0).setCellValue(initiative.getTitle());
                row.createCell(1).setCellValue(initiative.getDescription());
                row.createCell(2).setCellValue(initiative.getOwner());
                row.createCell(3).setCellValue(initiative.getCategory());
                row.createCell(3).setCellValue(initiative.getCreationdate());

                 
            }
            
           
        
          

        } catch ( Exception ex) {
            throw new Exception("Error generating summary report",ex);
        }
    }

    @Override
    protected void buildExcelDocument(Map<String, Object> map, HSSFWorkbook hssfw, HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
     summaryReport((List<Initiative>) map.get("initiatives"));
    }

    
}
