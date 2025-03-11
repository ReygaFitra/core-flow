package com.reyga.dev.services.base;

import com.reyga.dev.enumeration.ReportType;
import com.reyga.dev.exceptions.AppFaultException;
import com.reyga.dev.utils.CommonLogger;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

public abstract class BaseJasperReportService<RES, CONTENT> {

    protected final CommonLogger logger = new CommonLogger();

    protected <DATA> RES execute(String templatePath, String outputPath, String setFileName, Map<String, Object> reportParameters, List<DATA> dataList, String reportType) throws AppFaultException {
        String preProcessWithGeneratedId = preProcessOrGenerateId();

        CONTENT constructedContentDto = null;

        if (ReportType.CSV.getType().equalsIgnoreCase(reportType) || ReportType.XLSX.getType().equalsIgnoreCase(reportType)
                || ReportType.PDF.getType().equalsIgnoreCase(reportType) || ReportType.HTML.getType().equalsIgnoreCase(reportType) ||
                ReportType.XML.getType().equalsIgnoreCase(reportType) || ReportType.DOC.getType().equalsIgnoreCase(reportType) || ReportType.DOCX.getType().equalsIgnoreCase(reportType)) {
            throw new AppFaultException("04", "unsupported file type", "type provided : ".concat(reportType), HttpStatus.BAD_REQUEST);
        }

        try {

            generateReportProcess(templatePath, outputPath, setFileName, reportParameters, dataList);

            constructedContentDto = exportReportProcess(reportType, preProcessWithGeneratedId);

            uploadProcess();

        } catch (Exception e) {

            errorHandlingProcess(e);

        }


        return responseProcess(constructedContentDto);
    };

    protected abstract String preProcessOrGenerateId();

    protected abstract <DATA> void generateReportProcess(String templatePath, String outputPath, String setFileName, Map<String, Object> reportParameters, List<DATA> dataList);

    protected abstract CONTENT exportReportProcess(String reportType, String id);

    protected abstract void errorHandlingProcess(Exception e);

    protected abstract RES responseProcess(CONTENT contentDto);

    protected abstract void uploadProcess();
}
