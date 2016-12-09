/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hawkbyte.managers;

import com.hawkbyte.model.Initiative;
import com.hawkbyte.report.*;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author raizo
 */
public class ReportManager {
    private DetailedReport _DETAILED_REPORT;
    private SummaryReport _SUMMARY_REPORT;
    
    public ReportManager() throws SQLException, ClassNotFoundException{
        _DETAILED_REPORT = new DetailedReport();
        _SUMMARY_REPORT = new SummaryReport();
    
    }
    
public void detailedReport(List<Initiative>initiatives) throws Exception{
    _DETAILED_REPORT.detailedReport(initiatives);

}

public void summaryReport(List<Initiative>initiatives) throws Exception{

    _SUMMARY_REPORT.summaryReport(initiatives);
}

    
}
