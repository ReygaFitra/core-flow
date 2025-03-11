package com.reyga.dev.services;

import com.reyga.dev.dto.responses.ReportDetailsDto;
import com.reyga.dev.exceptions.AppFaultException;

import java.util.List;
import java.util.Map;

public interface JasperReportService {
    <DATA> ReportDetailsDto generateReport(String templatePath, String outputPath, String setFileName, Map<String, Object> reportParameters, List<DATA> dataList, String reportType) throws AppFaultException;
}
