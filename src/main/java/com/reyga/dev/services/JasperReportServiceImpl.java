package com.reyga.dev.services;

import com.reyga.dev.dto.content.JasperReportContentDto;
import com.reyga.dev.dto.responses.ReportDetailsDto;
import com.reyga.dev.enumeration.ReportType;
import com.reyga.dev.exceptions.AppFaultException;
import com.reyga.dev.utils.CommonLogger;
import com.reyga.dev.utils.DateUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXmlExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class JasperReportServiceImpl implements JasperReportService {

    private final CommonLogger logger = new CommonLogger();

    @Override
    public <DATA> ReportDetailsDto generateReport(String templatePath, String outputPath, String setFileName, Map<String, Object> reportParameters, List<DATA> dataList, String reportType) throws AppFaultException {
        JasperReportContentDto contentDto = new JasperReportContentDto();
        String id = UUID.randomUUID().toString();

        if (ReportType.CSV.getType().equalsIgnoreCase(reportType) || ReportType.XLSX.getType().equalsIgnoreCase(reportType)
                || ReportType.PDF.getType().equalsIgnoreCase(reportType) || ReportType.HTML.getType().equalsIgnoreCase(reportType) ||
                ReportType.XML.getType().equalsIgnoreCase(reportType) || ReportType.DOC.getType().equalsIgnoreCase(reportType) || ReportType.DOCX.getType().equalsIgnoreCase(reportType)) {
            throw new AppFaultException("04", "unsupported file type", "type provided : ".concat(reportType), HttpStatus.BAD_REQUEST);
        }

        InputStream templateSource;
        Path path = Paths.get(templatePath);

        try(InputStream newInputStream = Files.newInputStream(path)) {
            if (Files.exists(path)) {
                templateSource = newInputStream;
            } else {
                templateSource = new ClassPathResource(templatePath).getInputStream();
            }

            InputStream fileStream = templateSource;
            JasperReport report = JasperCompileManager.compileReport(fileStream);

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dataList);
            JasperPrint print = JasperFillManager.fillReport(report, reportParameters, dataSource);

            String reportFileName = setFileName.concat(".").concat(reportType.toLowerCase());
            Path reportPath = Paths.get(outputPath, reportFileName);
            Files.createDirectories(reportPath.getParent());
            long reportSize = Files.size(reportPath);

            contentDto.setReportFileName(reportFileName);
            contentDto.setReportPath(reportPath);
            contentDto.setReportSize(reportSize);

            OutputStream outputStream = new FileOutputStream(reportPath.toFile());

            switch (reportType) {
                case "csv":
                    // Export to CSV
                    contentDto.setReportId(ReportType.CSV.getType().concat("_").concat(id));
                    JRCsvExporter csvExporter = new JRCsvExporter();
                    csvExporter.setExporterInput(new SimpleExporterInput(print));
                    csvExporter.setExporterOutput(new SimpleWriterExporterOutput(outputStream));
                    csvExporter.exportReport();
                    contentDto.setDateCreated(DateUtil.getTimestamp(LocalDateTime.now()));
                    break;
                case "xlsx":
                    // Export to XLSX
                    contentDto.setReportId(ReportType.XLSX.getType().concat("_").concat(id));
                    JRXlsxExporter xlsxExporter = new JRXlsxExporter();
                    xlsxExporter.setExporterInput(new SimpleExporterInput(print));
                    xlsxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
                    xlsxExporter.exportReport();
                    contentDto.setDateCreated(DateUtil.getTimestamp(LocalDateTime.now()));
                    break;
                case "html":
                    // Export to HTML
                    contentDto.setReportId(ReportType.HTML.getType().concat("_").concat(id));
                    HtmlExporter htmlExporter = new HtmlExporter();
                    htmlExporter.setExporterInput(new SimpleExporterInput(print));
                    htmlExporter.setExporterOutput(new SimpleHtmlExporterOutput(outputStream));
                    htmlExporter.exportReport();
                    contentDto.setDateCreated(DateUtil.getTimestamp(LocalDateTime.now()));
                    break;
                case "xml":
                    // Export to XML
                    contentDto.setReportId(ReportType.XML.getType().concat("_").concat(id));
                    JRXmlExporter xmlExporter = new JRXmlExporter();
                    xmlExporter.setExporterInput(new SimpleExporterInput(print));
                    xmlExporter.setExporterOutput(new SimpleXmlExporterOutput(outputStream));
                    xmlExporter.exportReport();
                    contentDto.setDateCreated(DateUtil.getTimestamp(LocalDateTime.now()));
                    break;
                case "doc":
                    // Export to DOCX (RTF format)
                    contentDto.setReportId(ReportType.DOC.getType().concat("_").concat(id));
                    JRRtfExporter docxExporter = new JRRtfExporter();
                    docxExporter.setExporterInput(new SimpleExporterInput(print));
                    docxExporter.setExporterOutput(new SimpleWriterExporterOutput(outputStream));
                    docxExporter.exportReport();
                    contentDto.setDateCreated(DateUtil.getTimestamp(LocalDateTime.now()));
                    break;
                case "pdf":
                    // Export to PDF
                    contentDto.setReportId(ReportType.PDF.getType().concat("_").concat(id));
                    JasperExportManager.exportReportToPdfStream(print, outputStream);
                    contentDto.setDateCreated(DateUtil.getTimestamp(LocalDateTime.now()));
                    break;
                default:
                    throw new JRRuntimeException("Unsupported report type: " + reportType);
            }

        } catch (Exception e) {
            if (e instanceof IOException) {
                logger.exception("I/O Flow", "IOException Occurred", e);
                throw new AppFaultException("99", "SYSTEM ERROR", null, HttpStatus.INTERNAL_SERVER_ERROR);
            } else if (e instanceof JRException) {
                logger.exception("Creating Report Failed", "JRException Occurred", e);
                throw new AppFaultException("99", "SYSTEM ERROR", null, HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                logger.exception("Creating Report General", null, e);
                throw new AppFaultException("99", "GENERAL ERROR", null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return ReportDetailsDto.Builder.newBuilder()
                .id(contentDto.getReportId())
                .fileName(contentDto.getReportFileName())
                .fileType((".").concat(reportType.toLowerCase()))
                .filePath(contentDto.getReportPath().toString())
                .fileSize(contentDto.getReportSize())
                .dateCreated(contentDto.getDateCreated())
                .isStoredInObjectStorage(false)
                .build();
    };
}
